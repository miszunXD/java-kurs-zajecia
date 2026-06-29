package werehouse_inventory.service;

import werehouse_inventory.model.CategoryStats;
import werehouse_inventory.model.Product;
import werehouse_inventory.model.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Warehouse {
    private Map<String, Integer> stock;
    private Map<String, Set<Product>> catalog;
    private LinkedList<Transaction> transactionHistory;
    private PriorityQueue<Product> restockProduct;
    private Map<String, Product> skuToProduct;

    public Warehouse() {
        this.stock = new ConcurrentHashMap<>();
        // Przy działaniu wielowątkowym w środowisku produkcyjnym, wiele wątków może jednocześnie przyjmować
        // dostawy i realizować zamówienia. HashMapa spowodowałaby utratę danych i kolizje. ConcurrentHashMap
        // nas przed chroni dzięki CAS(Compare and Swap).
        this.catalog = new HashMap<>();
        this.transactionHistory = new LinkedList<>();
        this.skuToProduct = new HashMap<>();
        this.restockProduct = new PriorityQueue<>(Comparator.comparing(Product::price));
    }

    public void receiveDelivery(Product product, int quantity) {
        catalog.computeIfAbsent(product.category(), k -> new HashSet<>()).add(product);
        stock.merge(product.sku(), quantity, Integer::sum);
        transactionHistory.addLast(new Transaction(product, quantity, LocalDateTime.now(), "IN"));
        skuToProduct.put(product.sku(), product);
    }

    public boolean fulfillOrder(String sku, int quantity) {
        Integer current = stock.get(sku);

        if (stock.containsKey(sku) && quantity <= current) {
            stock.put(sku, current - quantity);
            Product product = skuToProduct.get(sku);
            transactionHistory.addLast(new Transaction(product, quantity, LocalDateTime.now(), "OUT"));
            return true;
        }
        return false;
    }

    public Map<String, Long> getStockByCategory() {
       Map<String, Long> result = new HashMap<>();

       for (Map.Entry<String, Integer> entry : stock.entrySet()) {
           String sku = entry.getKey();
           long quantity = entry.getValue();
           Product product = skuToProduct.get(sku);
           String category = product.category();

           result.merge(category, quantity, Long::sum);
       }
       return result;
    }

    public List<Product> getLowStockProducts(int threshold) {
        return stock.entrySet().stream()
                .filter(entry -> entry.getValue() < threshold)
                .map(entry -> skuToProduct.get(entry.getKey()))
                .toList();
    }

    public List<Transaction> getRecentTransactions(int n) {
        return Collections.unmodifiableList(transactionHistory.stream()
                .skip(Math.max(0, transactionHistory.size() - n))
                .toList());
    }

    public Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(skuToProduct.get(sku));
    }

    public PriorityQueue<Product> getRestockProduct() {
        return restockProduct;
    }

    public Map<String, CategoryStats> getStatsByCategory() {
        Map<String, CategoryStats> result = new HashMap<>();

        for (Map.Entry<String, Set<Product>> entry : catalog.entrySet()) {
            String category = entry.getKey();
            Set<Product> products = entry.getValue();

            long count = products.size();
            BigDecimal totalValue = products.stream()
                    .map(Product::price)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal averagePrice = totalValue.divide(
                    new BigDecimal(count), 2, RoundingMode.HALF_UP);

            result.put(category, new CategoryStats(count, averagePrice, totalValue));
        }
        return result;
    }
}

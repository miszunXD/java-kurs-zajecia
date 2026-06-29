package werehouse_inventory;

import werehouse_inventory.model.Product;
import werehouse_inventory.service.Warehouse;

import java.math.BigDecimal;

public class WarehouseApp {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        //Produkty:
        Product book = new Product("PROD-001", "Ogniem i Mieczem", "Books",
                new BigDecimal("49.90"));
        Product computer = new Product("PROD-002", "Macbook Air", "Computers",
                new BigDecimal("1499.99"));
        Product phone = new Product("PROD-003", "iPhone 17", "Smartphones",
                new BigDecimal("6499.99"));
        Product charger = new Product("PROD-004", "Wallcharger", "Adapters",
                new BigDecimal("49.99"));
        Product carplay = new Product("PROD-005", "Wireless CarPlay Adpater",
                "Adapters", new BigDecimal("64.99"));

        //Przyjmujemy dostawy
        warehouse.receiveDelivery(book, 5);
        warehouse.receiveDelivery(computer, 1);
        warehouse.receiveDelivery(phone, 4);
        warehouse.receiveDelivery(charger, 3);
        warehouse.receiveDelivery(carplay, 10);
        warehouse.receiveDelivery(book, 20);
        warehouse.receiveDelivery(phone, 8);
        warehouse.receiveDelivery(charger, 15);
        warehouse.receiveDelivery(computer, 3);
        warehouse.receiveDelivery(carplay, 6);

        //Kolejka uzupełniania stanu
        warehouse.getRestockProduct().add(book);
        warehouse.getRestockProduct().add(computer);
        warehouse.getRestockProduct().add(phone);
        warehouse.getRestockProduct().add(charger);
        warehouse.getRestockProduct().add(carplay);



        //Zamówienia
        warehouse.fulfillOrder("PROD-002", 1);
        warehouse.fulfillOrder("PROD-003", 2);
        warehouse.fulfillOrder("PROD-001", 5);
        warehouse.fulfillOrder("PROD-004", 2);
        warehouse.fulfillOrder("PROD-005", 5);
        warehouse.fulfillOrder("PROD-001", 3);
        warehouse.fulfillOrder("PROD-003", 1);
        warehouse.fulfillOrder("PROD-004", 4);
        warehouse.fulfillOrder("PROD-002", 2);
        warehouse.fulfillOrder("PROD-005", 3);

        //Raporty
        System.out.println("Stan wg KATEGORIA: ");
        warehouse.getStockByCategory().forEach((cat, qty) ->
                System.out.println(cat + ": " + qty));

        System.out.println("Stan poniżej 5 sztuk: ");
        warehouse.getLowStockProducts(5).forEach(System.out::println);

        System.out.println("Ostatnie 5 transkacji: ");
        warehouse.getRecentTransactions(5).forEach(System.out::println);

        System.out.println("Kolejka do uzupełnienia stocku: ");
        while (!warehouse.getRestockProduct().isEmpty()) {
            System.out.println(warehouse.getRestockProduct().poll());
        }

        System.out.println("Statyski kategorii: ");
        warehouse.getStatsByCategory().forEach((cat, stats) ->
                System.out.println(cat + ": " + stats));
    }
}

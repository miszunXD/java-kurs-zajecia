package smart_warehouse_logistic_system;

import java.util.ArrayList;
import java.util.List;

public class Warehouse <T extends Product>{
    private List<T> products = new ArrayList<>();

    public void addProduct(T product) {
        products.add(product);
    }

    public void showInventory() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public List<T> getProducts() {
        return products;
    }
}

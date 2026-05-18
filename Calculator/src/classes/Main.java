package classes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new FoodProduct("BruSer", 6, "Nabiał", "22.05.2026"));
        products.add(new FoodProduct("Parówki  BezMięska", 2, "Wędliny mięsopodobne", "16.05.2029"));
        products.add(new Electronics("Szajsung S26 Ultra", 6500, "Telefon", 24));
        products.add(new Product("Obroża sadomaso", 55, "Sexshop"));
        products.add(new Electronics("Mysz gamingowa MCHOSE", 290, "PC Mouse", 24));

        for (Product product : products)
            System.out.println(product.getDescription());
    }
}

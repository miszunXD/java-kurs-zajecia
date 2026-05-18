package classes;

import java.math.BigDecimal;

public class Product {
    private String name;
    private double price;
    private String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getDescription() {
        return "Produkt: " + name +
                ", cena: " + price +
                ", kategoria: " + category;
    }




}

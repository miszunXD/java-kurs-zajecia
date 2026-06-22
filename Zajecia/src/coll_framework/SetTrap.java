package coll_framework;

import java.util.Comparator;
import java.util.TreeSet;

record Product(String name, double price) {}

public class SetTrap {
    public static void main(String[] args) {
        // Porównujemy tylko i wyłącznie po cenie!
        TreeSet<Product> products = new TreeSet<>(Comparator.comparingDouble(Product::price)
                .thenComparing(Product::name));

        products.add(new Product("Myszka", 150.00));
        products.add(new Product("Klawiatura", 150.00));

        System.out.println("Rozmiar: " + products.size()); // Wypisze: 1!
    }
}
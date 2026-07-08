package streams.ecom;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("Laptop", 2000.0, true),
                new Product("Charger", 100.0, false),
                new Product("Laser", 500.0, true),
                new Product("CyganKebabMegaRollo", 32.90, true),
                new Product("Lody Tirówka", 50.0, true)
        );

        DoubleSummaryStatistics productsStats = products.stream()
                .mapToDouble(Product::price)
                .summaryStatistics();
        System.out.println("Najtańszy produkt w sklepie: " + productsStats.getMin()); //32.90
        System.out.println("Najdroższy produkt w sklepie: " + productsStats.getMax()); //2000
        System.out.println("Średnia cena produktów w sklepie: " + productsStats.getAverage()); //536.58

        boolean productsAvailability = products.stream()
                .allMatch(Product::isStock);
        System.out.println("Czy wszystkie produkty są dostępne? " + productsAvailability); //false

        boolean priceAboveOneK = products.stream()
                .anyMatch(p -> p.price() > 1000);
        System.out.println("Czy w sklepie znajduje się produkt droższy niż 1000 zł? " + priceAboveOneK); //true
    }
}

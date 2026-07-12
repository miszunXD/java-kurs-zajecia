package ecom_analyse;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("E01", "Laptop Lenovo IdeaPad 3", "Elektronika",
                        2899.99, 12, 45, 4.5),
                new Product("E02", "Smartfon Samsung Galaxy A55", "Elektronika",
                        1699.00, 30, 120, 4.6),
                new Product("E03", "Słuchawki JBL Tune 510BT", "Elektronika",
                        199.99, 80, 300, 4.3),
                new Product("E04", "Monitor LG 27\" 4K", "Elektronika",
                        1299.00, 15, 60, 4.7),
                new Product("E05", "Powerbank Anker 20000mAh", "Elektronika",
                        149.90, 100, 210, 4.4),

                // Książki
                new Product("K01", "Pan Tadeusz - Adam Mickiewicz", "Książki",
                        24.99, 50, 400, 4.9),
                new Product("K02", "Effective Java - Joshua Bloch", "Książki",
                        129.00, 20, 85, 4.8),
                new Product("K03", "Wiedźmin: Ostatnie Życzenie", "Książki",
                        34.90, 60, 500, 4.7),
                new Product("K04", "Atomic Habits - James Clear", "Książki",
                        44.90, 45, 320, 4.8),
                new Product("K05", "Clean Code - Robert C. Martin", "Książki",
                        139.00, 18, 95, 4.6),

                // AGD
                new Product("A01", "Robot kuchenny Bosch MUM5", "AGD",
                        899.00, 10, 40, 4.5),
                new Product("A02", "Czajnik elektryczny Philips", "AGD",
                        129.99, 40, 180, 4.3),
                new Product("A03", "Odkurzacz Dyson V8", "AGD",
                        1799.00, 8, 25, 4.7),
                new Product("A04", "Mikrofalówka Samsung 23L", "AGD",
                        449.00, 22, 70, 4.2),
                new Product("A05", "Ekspres do kawy DeLonghi", "AGD",
                        1299.00, 14, 55, 4.6),

                // Sport
                new Product("S01", "Rower górski Kross Level", "Sport",
                        2199.00, 6, 18, 4.5),
                new Product("S02", "Hantle regulowane 2x20kg", "Sport",
                        349.00, 25, 90, 4.4),
                new Product("S03", "Mata do jogi Nike", "Sport",
                        89.99, 70, 250, 4.6),
                new Product("S04", "Bidon termiczny 1L", "Sport",
                        49.90, 120, 400, 4.3),

                // Uroda
                new Product("U01", "Zestaw kosmetyków Nivea", "Uroda",
                        79.99, 55, 210, 4.4),
                new Product("U02", "Suszarka do włosów Dyson Supersonic", "Uroda",
                        1599.00, 9, 30, 4.8)
        );

        // 2.1 Top Bestseller
        System.out.println("=== TOP 5 BESTSELLER ===");
        products.stream()
                .sorted(Comparator.comparingDouble(p -> -p.price() * p.sold()))
                .limit(5)
                .forEach(p -> System.out.printf("%-20s %10.2f PLN%n", p.name(), p.price() * p.sold()));

        //2.2 Najwyższy raating wg kategorii
        System.out.println("=== KATEGORIA NAJWYŻSZA ŚREDNIA OCENA===");
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.averagingDouble(Product::rating)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresentOrElse(
                        entry -> System.out.printf("%s (średni rating: %.2f)%n",
                                entry.getKey(), entry.getValue()),
                        () -> System.out.println("Brak danych")
                );
        //2.3 Raport magazynowy
        System.out.println("=== Niski stan magazynowy stock < 10 ===");
        products.stream()
                .filter(p -> p.stock() < 10)
                .sorted(Comparator.comparingInt(Product::stock))
                .forEach(p -> System.out.printf("%s - ilość: %d%n", p.name(), p.stock()));

        //2.4
        System.out.println("=== Asortyment - liczba produktów per kategoria ===");
        products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.counting()
                ))
                .forEach((category, count) -> System.out.printf("%-15s %d sztuk%n", category, count));

        //2.5 Księgowość
        System.out.println("=== Całkowity przychód oraz średnia cena produktu z kategorii ===");
        Map<String, Double> revenueByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.summingDouble(p -> p.price() * p.sold())
                ));
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.averagingDouble(Product::price)
                ));
        revenueByCategory.forEach((category, revenue) ->
                System.out.printf("%-15s przychód: %10.2f PLN | średnia cena %8.2f PLN%n",
                        category, revenue, averagePriceByCategory.get(category)));

        //2.6 Okazje
        System.out.println("=== Okazje ===");
        products.stream()
                .filter(p -> p.rating() >= 4.5 && p.price() < 500)
                .sorted(Comparator.comparingDouble(Product::price))
                .forEach(p -> System.out.printf("%-35s %8.2f PLN%n", p.name(), p.price()));

        //2.7
        System.out.println("=== Czy wszystkie produkty posiadają rating > 0?");
        boolean aboveZeroRating = products.stream()
                .allMatch(p -> p.rating() > 0);
        System.out.println("Wynik: " + aboveZeroRating);

        //2.8
        System.out.println("=== Najdroższy produkt w sklepie ===");
        products.stream()
                .max(Comparator.comparingDouble(Product::price))
                .ifPresentOrElse(p -> System.out.printf("Produkt: %s | cena: %.2f PLN%n", p.name(), p.price()),
                        () -> System.out.println("Brak danych"));

        //2.9 Segmentacja
        System.out.println("=== Produkty Standard oraz Premium");
        Map<Boolean, List<Product>> partition = products.stream()
                .collect(Collectors.partitioningBy(p -> p.price() > 1000));
        System.out.println("Ilość produktów Standard (cena <= 1000): " + partition.get(false).size());
        System.out.println("Ilość produktów Premium (cena > 1000): " + partition.get(true).size());

        //2.10 Raport
        System.out.println("== RAPORT NAJLEPSZA OCENA W KAŻDEJ KATEGORII");
        Map<String, Optional<Product>> topRatedByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::category,
                        Collectors.maxBy(Comparator.comparingDouble(Product::rating))
                ));
        topRatedByCategory.forEach((category, optProduct) ->
                optProduct.ifPresent(p ->
                        System.out.printf("KAtegoria: %-12s | Najwyżej ocenniany produkt: %s (%.2f)%n",
                                category, p.name(), p.rating())));
    }
}

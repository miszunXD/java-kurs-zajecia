package log_tracker;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    private static final Map<String, Function<Double, Double>> discountStrategies = new HashMap<>();
    public static void main(String[] args) {

        /*
        1.1
        a - Predicate
        b - Consumer
        c - Supplier
        d - Function<T, R>

        1.2
        SAM - Single Abstract Method. Jest to interfejs funkcyjny z jedną metodą abstrakcyjną.
        Definicja interfejsu funkcjonalnego wskazuje, że takowy interfejs musi mieć jedną metodę abstracyjną,
        obecność metod default oraz static nie łamie zasady z definicji. Metody domyślne nie są abstrakcyjne,
        możemy więc stworzyć ich dowolną ilość w interfejsie. Metody statyczne przyypisane są do klasy,
        a nie jej instancji przez co nie są dziedziczone i nie trzeba ich implementować.
         */

//        // Zastany, stary kod:
        List<String> logs = new ArrayList<>(List.of("  error: db down  ", "  info: user logged  "));
//
//        logs.sort(new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareTo(s2);
//            }
//        });

        // 2.1 Przekształcenie na lambdę
        logs.sort((s1, s2) -> s1.compareTo(s2));

        // 2.2 Przekształcenie na Method Reference

        logs.sort(String::compareTo);
        // Referencja instacyjna na typie

        // 3. Predykaty

        Predicate<String> isNotNullOrEmpty = s ->
                s != null && !s.isBlank();

        Predicate<String> hasValidPrefix = s ->
                s.startsWith("EXP-");

        Predicate<String> hasCorrectLength = s ->
                s.length() == 10;

        Predicate<String> isValidTrackingCode = isNotNullOrEmpty.and(hasValidPrefix).and(hasCorrectLength);

        List<String> trackingCodes = List.of(
                "EXP-123456",
                "EXP-12345",
                "123456-EXP",
                "ABC-123456"
        );

        trackingCodes.forEach(tc -> System.out.println(tc + ": " + isValidTrackingCode.test(tc)));

        // 4 DispatchTable

        discountStrategies.put("VIP", price -> price * 0.80);
        discountStrategies.put("STUDENT", price -> price - 15.00);
        discountStrategies.put("STANDARD", Function.identity());

        System.out.println("Kody rabatowe ze zniżkami: ");
        calculateFinalPrice(1000, "VIP");
        calculateFinalPrice(1000, "STUDENT");
        calculateFinalPrice(1000, "STANDARD");
        calculateFinalPrice(1000, "ADMIN");
    }

    public static void calculateFinalPrice(double basePrice, String discountCode) {
        Function<Double, Double> strategy = discountStrategies.get(discountCode);

        if (strategy != null) {
            double discountApply = strategy.apply(basePrice);
            System.out.println("Cena po zniżce: " + discountApply);
        } else {
            throw new IllegalArgumentException("Ten kod zniżkowy nie istnieje! Kod:  " + discountCode);
        }
    }
}

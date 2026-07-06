package park_flow;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger();
        List<String> plates = List.of("WAW123", "KRA456", "POZ789");
        plates.forEach(plate -> {
            System.out.println("Zarejestrowano wjazd: " + plate);
            counter.incrementAndGet();
        });

        /*
        1.1
        Zmienna counter nie jest przechowywana w lambdzie. JVM tworzy kopie zmiennej. Nie można używać zmiennych
        które nie są effective final w lambdach. Problem jaki występuje to przy wielowątkowości może dojść do
        niespójności.

        1.2
        Przekazanie do metody Stringa powoduje jego natychmiastowanie wywowałanie. W przypadku interfejsu
        Supplier<String> przekazanie go spodowuje wywołanie, wtedy kiedy tego chcemy, wywołamy np lambdę z tym supplierem.

         */

        // Zastany, stary kod:
        Function<String, String> plateFormatter = new Function<String, String>() {
            @Override
            public String apply(String rawPlate) {
                return rawPlate.replace(" ", "").toUpperCase();
            }
        };

        // 2.1
        Function<String, String> plateFormatterLambda = rawPlate ->
                rawPlate.replace(" ", "").toUpperCase();

        // 2.2
        //Function<String, String> plateFormatterLambda = String::normalizePlate;

        //3.1
        Predicate<String> isNotNullOrBlank = ticket ->
                ticket != null && !ticket.isBlank();
        Predicate<String> hasValidPrefix = ticket ->
                ticket.startsWith("PARK-");
        Predicate<String> isInternalTest = ticket ->
                ticket.equals("TEST-000");

        Predicate<String> isValidTicket = isNotNullOrBlank.and(hasValidPrefix).or(isInternalTest);

        String ticket1 = "TEST-000";
        String ticket2 = "PARK-000";
        String ticket3 = "PARKED-000";

        System.out.println(ticket1 + ": " + isValidTicket.test(ticket1));
        System.out.println(ticket2 + ": " + isValidTicket.test(ticket2));
        System.out.println(ticket3 + ": " + isValidTicket.test(ticket3));

        //4.1
        PriceStrategy priceStrategy = new PriceStrategy();
        double basePrice = 10.0;

        System.out.println("MOTO: " + priceStrategy.calculateFee(basePrice, "MOTO"));
        System.out.println("CAR: " + priceStrategy.calculateFee(basePrice, "CAR"));
        System.out.println("BUS: " + priceStrategy.calculateFee(basePrice, "BUS"));
    }
}

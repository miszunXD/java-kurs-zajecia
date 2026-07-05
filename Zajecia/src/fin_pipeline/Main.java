package fin_pipeline;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {
        /*
        1.1
        Wyrażania lambda wymagają aby zmienne były effective final (niemutowalne). Dzieje się tak, gdyż zmienne znajdują
        się na stosie, JVM tworzy kopie wartości zmiennej przy tworzeniu lambdy i umieszcza ją w obiekcie na stercie.
        Ramka metody na stosie jest usuwana, gdy metoda kończy swoje działanie. Jeżeli lambda żyje dłuzej niż metoda,
        to zmienna już nie istnieje. JVM musi skopiować wartość zmiennej do obiektu.
        Jeśli zmienna nie byłaby effective final i zmieniliśby jej wartość, kopia w obiekcie byłaby inna niż oryginał
        przez co dostaniemy niezgodność stanu (stale data) i  brak gwarancji happens - before między wątkiem
        modyfikującym a wątkiem wykonującym lambdę. Kopia zmiennej w lambdzie może pokazywać niaktualną wartość.

        1.2
        orElseGet() jest efektywniejsze wydajnościowo, gdyż wykona się tylko w sytuacji, gdy Optional jest pusty.
        orElse() wykona się zawsze co jest niepotrzebne w tym przypadku pochłania zasoby.
         */

    @FunctionalInterface
    interface ThrowingFunction<T, R, E extends Exception> {
        R apply (T t) throws E;
    }

    private static <T, R, E extends Exception> Function<T, R> wrap(ThrowingFunction<T, R, E> throwingFunction) {
        return t -> {
            try {
                return throwingFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static void main(String[] args) {
        List<String> paths = List.of("data.txt", "config.xml");

        ThrowingFunction<String, String, IOException> readFile = p -> Files.readString(Path.of(p));

        Function<String, String> safeReadFile = wrap(readFile);

//        paths.forEach(p -> {
//            String content = safeReadFile.apply(p);
//            System.out.println("Zawartość " + p + ": " + content);
//        });

        Function<String, String> removeWhiteSpace = s -> s.replace(" ", "");
        Function<String, String> addPrefix = s -> "[TX] " + s;
        Function<String, String> convertToLower = String::toLowerCase;

        Function<String, String> pipelineA = removeWhiteSpace.andThen(addPrefix).andThen(convertToLower);
        System.out.println("Pipeline A result: " + pipelineA.apply(" 100 USD ")); //[tx] 100usd

        Function<String, String> pipelineB = removeWhiteSpace.compose(addPrefix).compose(convertToLower);
        System.out.println("Pipeline B result: " + pipelineB.apply(" 100 USD ")); //[TX]100usd

        /*
        Pipeline A wykonuje się od lewej do prawej tj. Usuwamy white space z tekstu, dodajemy prefix ze spacją,
        a następnie wszytko konwertujemy na małe litery.

        Pipeline B przy użyciu compose działa odwrotnie (od prawej do lewej wg zapisu). Najpierw zmieniamy napis na małe
        litery -> dodajemy prefix z wielkimi literami -> usuwamy spacje
         */

        Map<String, Supplier<String>> reportStrategies = new HashMap<>();

        reportStrategies.put("Q1", () -> {
            System.out.println("Pobieranie gigabajtów danych dla Q1...");
            return "Raport_Q1_Gotowy";
        });
        reportStrategies.put("Q2", () -> {
            System.out.println("Pobieranie gigabajtów danych dla Q2...");
            return "Raport_Q2_Gotowy";
        });

        Lazy<String> lazy = new Lazy<>(reportStrategies.get("Q1"));

        System.out.println("Program gotowy, czekam na użytkownika...");
        System.out.println(lazy.get());
        System.out.println(lazy.get());
        System.out.println(lazy.get());

     }
}

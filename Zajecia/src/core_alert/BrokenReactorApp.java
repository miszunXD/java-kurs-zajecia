package core_alert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

class ReactorDiagnostics {
    public static String triggerEmergencyCooling(String coolingSystemName) {
        System.out.println("[KRYTYCZNE] !!! URUCHAMIAM SYSTEM: " + coolingSystemName + " !!!");
        return "SYSTEM_ACTIVE";
    }
}

class AlertService {
    public void logAlert(String message) {
        System.out.println("[DIAGNOSTYKA] Zapisano alert: " + message);
    }
}

public class BrokenReactorApp {
    public static void main(String[] args) {
        double currentCoreTemp = 30.0; // Bezpieczna temperatura reaktora
        System.out.println("Status reaktora: BEZPIECZNY. Temperatura: " + currentCoreTemp + "C");

        // === ANTYWZORZEC 1 ===
        /*
        Wymagane było dodanie () -> czyli opakowanie w lambdę. W przypadku przed zmianą metoda
        wywoływała się natychmiast zamiast w momencie użycia get() w ifie
         */
        Supplier<String> emergencyAction = () -> ReactorDiagnostics.triggerEmergencyCooling("WaterCooling");

        if (currentCoreTemp > 500.0) {
            System.out.println("!!! ALARM !!!");
            emergencyAction.get();
        }

        // === ANTYWZORZEC 2 ===
        // Chcemy zliczyć ile odczytów przekroczyło normę ostrzegawczą (> 25.0)
        List<Double> temperatureHistory = List.of(26.4, 24.1, 28.9, 21.0, 31.5);
        AtomicInteger warningCounter = new AtomicInteger(0);

        temperatureHistory.forEach(temp -> {
            if (temp > 25.0) {
                /*
                Czy to podejście jest bezpieczne w systemach produkcyjnych?
                Nie, jest to obejście efektywnej finalnej. Na poziomie produkcyjnym, wielowątkowym,
                może tu wystapić błąd krytyczny i wynik może być błędny.
                 */
                warningCounter.incrementAndGet();
            }
        });
        System.out.println("Liczba odczytów ostrzegawczych: " + warningCounter.get());

        // === ANTYWZORZEC 3 ===
        // Wiązanie referencji do serwisu logowania
        AlertService alertService = null; // Serwis nie został jeszcze zainicjalizowany!

        // Definiujemy obsługę alertów za pomocą Method Reference
        Consumer<String> criticalAlertHandler = s -> {
            if (alertService != null) {
                alertService.logAlert(s);
            } else {
                System.out.println("Błąd! Alert: " + s);
            }
        };

        criticalAlertHandler.accept("Przekroczono ciśnienie w komorze!");

        // 3. Błąd występuje opóźniony, gdy Runtime próbuje wywołać metodę na nullu dopiero tutaj.
        //Błą∂ jest wyśtlany przez środowisko IDE


    }
}

package secure_cipher_strategy.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Event> events = new ArrayList<>();

        events.add(new Event("EV-001", "Festival", "Audioriver - festiwal techno", true));
        events.add(new Event("EV-002", "Festival", "Opener - koncerty dla normików", false));
        events.add(new Event("EV-003", "Concert", "JazzBez", false));
        events.add(new Event("EV-004", "Race", "F1 Zgierz", true));
        events.add(new Event("EV-005", "NightShopping", "Niesamowite obniżki w manufaktura już w ten weekend!", true));

        List<Predicate<Event>> isCriticalFilter = List.of(Event::isCritical);
        List<Predicate<Event>> isCriticalandLongDesc = List.of(
                Event::isCritical, event -> event.description().length() > 20
        );

        Function<Event, String> shortFormat = event -> event.id() + ": " + event.description();
        Function<Event, String> ifCriticalFunction = event -> {
            if (event.isCritical()) {
                return "PILNY EVENT: " + event.id() + ": " + event.description();
            } else {
                return "Standard event: " + event.id() + ": " + event.description();
            }
        };

        EventProcessor eventProcessor = new EventProcessor();
        Consumer<String> output = System.out::println;

        System.out.println("isCritical + krótki format: ");
        eventProcessor.process(
                events,
                isCriticalFilter,
                shortFormat,
                output
        );

        System.out.println("isCriticalAndLongDesc + ifCriticalFunct");
        eventProcessor.process(
                events,
                isCriticalandLongDesc,
                ifCriticalFunction,
                output
        );
    }
}
/*
1. and() jest znacznie czytelniejsze od warunków &&. Czytamy to naturalnie - metoda x AND (i)
metoda y AND ... and() daje nam również możliwość wieloktronego używania róznych predykatów.
Jest on również dzięki temu łatwiejszy w testowaniu i walidacji.
2. Podstawowo podobnie jak 1 pytanie - używania EncryptionStrategy jest czytelniejsze,
czytając kod możemy się domyślić o co chodzi zamiast czytać dokumentacje.
W systemie możemy mieć kilka różnych funcji, których kompilator nie odróżnia. Nasza EncryptionStrategy
jest bezpieczniejsza pod tym względem.
Możemy również rozszerzać nasz interfejs w przyszłości, w odróznieniu od generycznego Function.
 */

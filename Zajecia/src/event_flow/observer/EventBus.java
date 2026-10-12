package event_flow.observer;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private final Map<String, List<EventListener>> listeners = new ConcurrentHashMap<>();

    public void subscribe(String eventType, EventListener eventListener) {
        Objects.requireNonNull(eventType, "Typ zdarzenia nie może być null!");
        Objects.requireNonNull(eventListener, "Listener nie może być null");

        listeners.
                computeIfAbsent(eventType, key -> new CopyOnWriteArrayList<>())
                .add(eventListener);
    }

    public void publish(Event event) {
        Objects.requireNonNull(event, "Zdanie nie może być null");

        List<EventListener> subscribers = listeners.getOrDefault(event.type(), List.of());
        for (EventListener subscriber : subscribers) {
            try {
                subscriber.onEvent(event);
            } catch (Exception e) {
                System.err.printf("Jeden z subskrybentów rzucił wyjątek dla zdarzenia: %s%n", event.type() + ": " + e);
            }
        }
    }
}

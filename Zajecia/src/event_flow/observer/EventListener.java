package event_flow.observer;

@FunctionalInterface
public interface EventListener {
    void onEvent(Event event);
}

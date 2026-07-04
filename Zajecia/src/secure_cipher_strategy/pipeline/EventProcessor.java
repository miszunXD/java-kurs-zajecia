package secure_cipher_strategy.pipeline;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {
    public void process(
            List<Event> events,
            List<Predicate<Event>> filters,
            Function<Event, String> formatter,
            Consumer<String> output
    ) {
        Predicate<Event> combined = event -> true;

        for (Predicate<Event> filter : filters) {
            combined = combined.and(filter);
        }

        for (Event event : events) {
            if (combined.test(event)) {
                String formatted = formatter.apply(event);
                output.accept(formatted);
            }
        }
    }
}

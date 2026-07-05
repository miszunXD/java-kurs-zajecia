package secure_cipher_strategy.cipher;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MessageDispatcher {
    public void dispatch(
            List<Message> messages,
            EncryptionStrategy strategy,
            Predicate<Message> filter,
            Consumer<String> sender
    ) {
        for (Message m : messages) {
            if (filter.test(m)) {
                String encrypted = strategy.encrypt(m.content());
                sender.accept(encrypted);
            }
        }
    }
}

package secure_cipher_strategy.cipher;

import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<Message> messages = List.of(
                new Message("MSG-001", "Error!", 5),
                new Message("MSG-002", "Awaria systemu napięcia!", 7),
                new Message("MSG-003", "System zwrotny ciśnienia obciążony!", 3),
                new Message("MSG-004", "Do tej funkcji potrzeba statusu Admin!", 10)
        );
        EncryptionStrategy reverse = text ->
                new StringBuilder(text).reverse().toString().toUpperCase();

        EncryptionStrategy leetSpeak = text ->
                text.replace("a", "4")
                        .replace("A", "4")
                        .replace("e", "3")
                        .replace("E","3")
                        .replace("i", "1")
                        .replace("I", "1")
                        .replace("o", "0")
                        .replace("O", "0");

        EncryptionStrategy obfuscation = text -> {
            if (text.length() <= 2){
                return text;
            }
            StringBuilder result = new StringBuilder();
            result.append(text.charAt(0));

            for (int i = 1; i < text.length() - 1; i++) {
                result.append("*");
            }

            result.append(text.charAt(text.length() - 1));
            return result.toString();
        };

        MessageDispatcher messageDispatcher = new MessageDispatcher();
        Consumer<String> sender = text -> System.out.println("Message: " + text);

        System.out.println("Odwrócenie i wersaliki: ");
        messageDispatcher.dispatch(
                messages,
                reverse,
                message -> true,
                sender
        );

        System.out.println("LeetSpeak: ");
        messageDispatcher.dispatch(
                messages,
                leetSpeak,
                message -> message.content().length() < 20,
                sender
        );

        System.out.println("Obfuscation: ");
        messageDispatcher.dispatch(
                messages,
                obfuscation,
                message -> message.securityLevel() > 5,
                sender
        );
    }
}

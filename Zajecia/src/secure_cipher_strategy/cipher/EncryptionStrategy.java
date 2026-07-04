package secure_cipher_strategy.cipher;

@FunctionalInterface
public interface EncryptionStrategy {
    String encrypt(String text);
}

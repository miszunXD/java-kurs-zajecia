package safe_port;

public class PortValidation {
    public static int parseAndValidatePort(String rawPort) {
        if (rawPort == null || rawPort.isBlank()) {
            throw new IllegalArgumentException("Port nie może być pusty!");
        }
        int port;

        try {
            port = Integer.parseInt(rawPort);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Port musi być liczbą! Otrzymano: " + rawPort);
        }

        if (port < 1024 || port > 65535) {
            throw new IllegalArgumentException("Port poza bezpiecznym zakresem (1024-65535");
        }

        return port;
    }
}

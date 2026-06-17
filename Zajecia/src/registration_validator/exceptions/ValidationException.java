package registration_validator.exceptions;

public class ValidationException extends RegistrationException {
    private final String field;

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}

package registration_validator.exceptions;

public class DuplicateEmailException extends RegistrationException {
    public DuplicateEmailException(String email) {
        super("Taki email już istnieje! " + email);
    }
}

package registration_validator.model;

public enum PasswordStrength {
    WEAK("Hasło jest za słabe."),
    OK("Hasło spełnia wymagania."),
    STRONG("Hasło jest silne.");

    private final String description;

    PasswordStrength(String description) {
        this.description = description;
    }


}

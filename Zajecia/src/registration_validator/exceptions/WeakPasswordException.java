package registration_validator.exceptions;

import java.util.List;

public class WeakPasswordException extends RegistrationException {
    private final int score;
    private final List<String> details;

    public WeakPasswordException(List<String> details, int score) {
        super("Hasło jest za słabe! Score: " + score);
        this.details = details;
        this.score = score;
    }

    public List<String> getDetails() {
        return details;
    }

    public int getScore() {
        return score;
    }


}

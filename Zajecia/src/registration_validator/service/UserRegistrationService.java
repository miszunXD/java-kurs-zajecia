package registration_validator.service;

import registration_validator.exceptions.DuplicateEmailException;
import registration_validator.exceptions.ValidationException;
import registration_validator.exceptions.WeakPasswordException;
import registration_validator.model.PasswordStrength;
import registration_validator.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRegistrationService {
    private final Map<String, User> users = new HashMap<>();

    public void registerUser(String name, String email, String password) {
        validateName(name);
        validateEmail(email);
        validateUniqueEmail(email);
        validatePasswordOrThrow(password);
        String hashPassword = hashedPassword(password);
        addUser(name, email, hashPassword);

    }

    private void addUser(String name, String email, String hashPassword) {
        User user = new User(name, email, hashPassword);
        users.put(email, user);
    }

    private String hashedPassword(String password) {
        return "hashed_" + password;
    }

    private void validatePasswordOrThrow(String password) {
        PasswordStrength strength = validatePassword(password);

        if (strength == PasswordStrength.WEAK) {
            int score = calculatePasswordScore(password);
            List<String> details = collectPasswordDetails(password);
            throw new WeakPasswordException(details, score);
        }
    }

    private void validateUniqueEmail(String email) {
        if (users.containsKey(email)) {
            throw new DuplicateEmailException(email);
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("email", "Email nie może być pusty!");
        }

        if (!email.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
            throw new ValidationException("email", "Email musi zawierać poprawne znaki!");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("name", "Imię nie może być puste!");
        }

        if (name.length() < 2 || name.length() > 100) {
            throw new ValidationException("name", "Imię musi mieścić się w zakresie 2 - 100 znaków!");
        }

        if (!name.matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\s\\-]+$")) {
            throw new ValidationException("name", "Imię może zawierać tylko litery, spacje i myślniki!");
        }
    }

    public PasswordStrength validatePassword(String password) {
        int score = calculatePasswordScore(password);

        if (score <= 1) {
            return PasswordStrength.WEAK;
        }

        if (score <= 3) {
            return PasswordStrength.OK;
        } else {
            return PasswordStrength.STRONG;
        }
    }

    private int calculatePasswordScore(String password) {
        if (password == null) {
            throw new RuntimeException("Hasło nie może być null!");
        }
        int score = 0;

        if (password.length() >= 8) {
            score++;
        }

        if (password.matches(".*[A-Z].*")) {
            score++;
        }

        if (password.matches(".*\\d.*")) {
            score++;
        }

        if (password.matches(".*[^a-zA-Z0-9].*")) {
            score++;
        }

        return score;
    }

    private List<String> collectPasswordDetails(String password) {
        List<String> details = new ArrayList<>();

        if (password.length() < 8) {            details.add("Hasło jest za krótkie - min. 8 znaków!");
        }

        if (!password.matches(".*[A-Z].*")) {
            details.add("Hasło nie zawiera wielkich liter!");
        }

        if (!password.matches(".*\\d.*")) {
            details.add("Hasło nie zawiera cyfr!");
        }

        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            details.add("Hasło nie zawiera znaków specjalnych!");
        }

        return details;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}

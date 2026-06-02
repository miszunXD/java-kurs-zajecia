package adnotacje_bigdecimal_string;

public class StringMain {
    public static String validatePassword(String password) {
        String stripped = password.strip();

        boolean hasEightChars = stripped.length() >= 8;
        boolean hasNoEmptySpaces = stripped.equals(password);
        boolean hasSpecialSignsOrDigits = false;
        for (char c : stripped.toCharArray()) {
            if (Character.isDigit(c) || !Character.isLetterOrDigit(c)) {
                hasSpecialSignsOrDigits = true;
                break;
            }
        }

        if (!hasEightChars) {
            System.out.println("Hasło jest za krótkie!");
        }

        if (!hasNoEmptySpaces) {
            System.out.println("Hasło ma puste pola!");
        }

        if (!hasSpecialSignsOrDigits) {
            System.out.println("Hasło musi zawierać znaki specjalne lub cyfry!");
        }

        return stripped;
    }

    public static void main(String[] args) {
        validatePassword("MojeSuperTajneHaslo123!");
        validatePassword("Syf");
        validatePassword("SyfSyfSyf123!");
    }
}

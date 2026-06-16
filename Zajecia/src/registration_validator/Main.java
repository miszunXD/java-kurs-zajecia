package registration_validator;

import registration_validator.exceptions.DuplicateEmailException;
import registration_validator.exceptions.ValidationException;
import registration_validator.exceptions.WeakPasswordException;
import registration_validator.service.UserRegistrationService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var service = new UserRegistrationService();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("=== System rejestracji ===");
                System.out.println("1. Zarejestruj użytkownika");
                System.out.println("2. Wypisz użytkowników");
                System.out.println("3. Wyjdź");
                System.out.print("Wybierz opcję: ");

                String choice = sc.nextLine();

                switch (choice) {
                    case "1" -> {
                        boolean done = false;
                        while (!done) {
                            System.out.print("Podaj imię: ");
                            String name = sc.nextLine();
                            System.out.print("Podaj email: ");
                            String email = sc.nextLine();
                            System.out.print("Podaj hasło: ");
                            String password = sc.nextLine();

                            try {
                                service.registerUser(name, email, password);
                                System.out.println("Zarejestrowano użytkownika!");
                                done = true;

                            } catch (ValidationException e) {
                                System.out.println("Błąd walidacji: " + e.getField() + " | " + e.getMessage());
                                System.out.println("Spróbuj ponownie.");

                            } catch (DuplicateEmailException e) {
                                System.out.println(e.getMessage());
                                done = true;

                            } catch (WeakPasswordException e) {
                                System.out.println("Hasło za słabe (score: " + e.getScore() + "/4):");
                                e.getDetails().forEach(System.out::println);
                                System.out.println("Spróbuj ponownie.");
                            }
                        }
                    }
                    case "2" -> {
                        if (service.getUsers().isEmpty()) {
                            System.out.println("Baza jest pusta.");
                        } else {
                            System.out.println("---Stan bazy---");
                            service.getUsers().forEach((e, user) -> System.out.println(user));
                        }
                    }
                    case "3" -> {
                        System.out.println("Do widzenia!");
                        return;
                    }
                    default -> System.out.println("Nieznana opcja. Wybierz 1-3.");
                }
            }
        }
    }
}
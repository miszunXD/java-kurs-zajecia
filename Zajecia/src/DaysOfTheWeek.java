import java.util.Scanner;

public class DaysOfTheWeek {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj nazwę dnia tygodnia z zakresu 1 - 7: ");
        int day = sc.nextInt();

        String name = switch (day) {
            case 1 -> "Poniedziałek";
            case 2 -> "Wtorek";
            case 3 -> "Środa";
            case 4 -> "Czwartek";
            case 5 -> "Piątek";
            case 6 -> "Sobota";
            case 7 -> "Niedziela";
            default -> null;
        };

        if (name == null) {
            System.err.println("BŁĄD - wybierz numer z zakresu 1 - 7");
            return;
        }

        String typDnia = (day <= 5) ? "Dzień roboczy" : "Weekend";


        String autobus;
        if (day <= 5) {
            autobus = "Odjazd autobusu o 5;30";
        } else if (day == 6) {
            autobus = "Odjazd autobusu o 7;00";
        } else {
            autobus = "Odjazd autobusu o 9;00";
        }

        System.out.println(name + " | " + typDnia + " | " + autobus);

        sc.close();
    }
}

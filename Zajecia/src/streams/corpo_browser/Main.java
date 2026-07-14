package streams.corpo_browser;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Department deptIT = new Department("IT", List.of(
                new Employee("Janys", 30, 5000),
                new Employee("Andrzej", 25, 6000),
                new Employee("Filip", 29, 4000)
        ));

        Department deptHR = new Department("HR", List.of(
                new Employee("Judyta", 19, 1600),
                new Employee("Jessica", 25, 2000),
                new Employee("Apolonia", 65, 3000)
        ));

        List<Department> departments = List.of(deptIT, deptHR);

        System.out.println("Pracownicy ze wszystkich działów w firmie, posortowani alfabetycznie: ");
        departments.stream()
                .flatMap(e -> e.employees().stream())
                .map(Employee::name)
                .sorted()
                .forEach(System.out::println);

        OptionalDouble maxSalary = departments.stream()
                .flatMap(e -> e.employees().stream())
                .mapToDouble(Employee::salary)
                .max();

        //Użycie ifPresentOrElse, ładniejszy wydruk zamiast OptionalDouble[6000] + ochrona przed pustym
        //strumieniem
        maxSalary.ifPresentOrElse(
                salary -> System.out.println("Najwyższa pensja wynosi: " + salary),
                () -> System.out.println("Brak pracowników w firmie!")
        );

        String reduced = departments.stream()
                .flatMap(e -> e.employees().stream())
                .map(Employee::name)
                .sorted()
                .reduce("", (a, b) -> a.isEmpty() ? b : a + " | " + b);
        System.out.println("[KADRY] -> " + reduced);

        //Nowoczesna wersja Javy dla reduce, użycie collect
        String collectorsJoining = departments.stream()
                .flatMap(e -> e.employees().stream())
                .map(Employee::name)
                .sorted()
                .collect(Collectors.joining(" | ", "[KADRY] -> ", ""));
        System.out.println(collectorsJoining);
    }
}

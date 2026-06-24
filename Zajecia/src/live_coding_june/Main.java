package live_coding_june;

import live_coding_june.model.Aircraft;
import live_coding_june.model.Passport;
import live_coding_june.service.FlightControl;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        HashSet<Passport> passports = new HashSet<>();

        passports.add(new Passport("EE99212", "Kowalski"));
        passports.add(new Passport("EE99212", "Nowak"));


        System.out.println("Rozmiar HashSetu: ");
        System.out.println(passports.size());

        FlightControl flightControl = new FlightControl();

        flightControl.assignPassengerToGate("GATE-A", "Kowalski");
        flightControl.assignPassengerToGate("GATE-A", "Nowak");
        flightControl.assignPassengerToGate("GATE-B", "Wiśniewski");

        flightControl.registerDeparture(new Aircraft("Charters-707", 1));
        flightControl.registerDeparture(new Aircraft("Emergency-Med", 10));
        flightControl.registerDeparture(new Aircraft("Cargo-Flight", 4));

        flightControl.processTakeoffs();

        /*
        1.1
        Iterowanie po mapie jest mniej wydajne, gdyż dla każdego klucza musimy szukać wartości w
        odpowiednim buckecie. entrySet() posiada para klucz-wartość co znacznie skraca proces
        wyszukiwania elementów.

        1.2
        Przy wielowątkowej pracy, zwykła HashMapa może mieć problem z race condition.
        Możemy utracić dane, źle liczyć wartości. Osobne wątki mogą podwójnie policzyć wartości
        przy iteracjach, zakłamując wynik, który jest trudny do wykrycia.

        java.utill.concurrent -> ConcurrentHashMap
         */
    }
}

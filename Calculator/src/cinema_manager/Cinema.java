package cinema_manager;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private List<Ticket> tickets = new ArrayList<>();
    private Manager manager;

    public Cinema(Manager manager){
        this.manager = manager;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static void main(String[] args) {

        //Tworzenie obiektu Manager oraz Cinema
        Manager manager = new Manager("Ebzdyberydencjusz Obsztyfitykultykiewiczewski", 3500, 450);
        Cinema cinema = new Cinema(manager);


        //Tworzenie biletów
        cinema.tickets.add(new Ticket("Mandalorian i Grogu", 25.50));
        cinema.tickets.add(new Ticket("Peruwiańskie Oranie", 45.00));

        //Korzystanie z fabryki
        MovieShow marathon = MovieShow.createMarathon("Władca Pierścieni");
        MovieShow marathon2 = MovieShow.createMarathon("Gwiezdne Wojny");

        cinema.tickets.add(new Ticket(marathon.getTitle(), marathon.getTicketPrice()));
        cinema.tickets.add(new Ticket(marathon2.getTitle(), marathon2.getTicketPrice()));

        //Podsumowanie finansowe kina oraz pensja managera
        System.out.println("Łączny przychód kina: " + Ticket.getTotalRevenue());
        System.out.println("Wynagrodzenie pracowników: ");
        System.out.printf("Manager: %s, podstawa pensji: %.2f + bonus: %.2f = %.2f zł%n",
                manager.name,
                manager.baseSalary,
                manager.getBonus(),
                manager.calculateSalary());

        //Sprawdzenie działania zwiększania liczby id przy dodawaniu biletów, bazowo = 1000;
        System.out.println("Ostatni numer ID: " + Ticket.getIdCounter());

        //Walidacja biletów -> iteracji, nazw filmów i cen
        for (Ticket t : cinema.tickets) {
            System.out.println(t);
        }
    }
}

package event_flow;

import event_flow.observer.EventBus;
import event_flow.observer.TicketBookedEvent;
import event_flow.strategy.CinemaDiscounts;
import event_flow.strategy.PriceCalculator;

public class Main {
    public static void main(String[] args) {

        double price = 50;
        PriceCalculator calc = new PriceCalculator(CinemaDiscounts.seniorDiscount);
        printDiscount(price, calc);

        calc.setStrategy(CinemaDiscounts.familyDiscount);
        printDiscount(price, calc);

        calc.setStrategy(CinemaDiscounts.noDiscount);
        printDiscount(price, calc);

        calc.setStrategy(CinemaDiscounts.studentDiscount);
        printDiscount(price, calc);

        EventBus bus = new EventBus();

        bus.subscribe("TICKET_BOOKED", event -> {
            if (event instanceof TicketBookedEvent e) {
                System.out.printf("Zaksięgowano bilet: %s%n", e.ticketId());
            }
        });

        bus.subscribe("TICKET_BOOKED", event -> {
            throw new RuntimeException("SMS Gateway Offline!");
        });

        bus.subscribe("TICKET_BOOKED", event -> {
            if (event instanceof TicketBookedEvent e) {
                System.out.printf("Wysłano email na: %s%n", e.customerEmail());
            }
        });

        bus.publish(new TicketBookedEvent("TICKET-001", "walaszek2137@buziaczek.pl"));

    }

    private static void printDiscount(double price, PriceCalculator calc) {
        System.out.printf("Cena: %.2f, cena po zniżce:  %.2f%n",
                price,
                calc.calculate(price));
    }
}

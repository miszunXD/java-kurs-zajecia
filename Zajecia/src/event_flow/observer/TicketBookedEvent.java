package event_flow.observer;

public record TicketBookedEvent(String ticketId, String customerEmail) implements Event {
    @Override
    public String type() {
        return "TICKET_BOOKED";
    }
}

package ticket_flow;

import ticket_flow.controller.TicketController;
import ticket_flow.dto.CreateTicketRequest;
import ticket_flow.dto.TicketResponse;
import ticket_flow.repository.InMemoryTicketRepository;
import ticket_flow.repository.TicketRepository;
import ticket_flow.service.TicketService;
import ticket_flow.service.TicketServiceImpl;

public class Main {
    public static void main(String[] args) {
        // 1. Inicjalizacja warstw od dołu do góry
        TicketRepository repo = new InMemoryTicketRepository();
        TicketService service = new TicketServiceImpl(repo);
        TicketController controller = new TicketController(service);

        // 2. Symulacja żądań od klienta
        TicketResponse response1 = controller.handleCreate(
                new CreateTicketRequest("Brak internetu", "Router nie świeci"));
        System.out.println("Utworzono: " + response1);

        TicketResponse resolved = controller.handleResolve(response1.id());
        System.out.println("Zaktualizowano: " + resolved);
    }
}

package ticket_flow.controller;

import ticket_flow.dto.CreateTicketRequest;
import ticket_flow.dto.TicketResponse;
import ticket_flow.service.TicketService;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TicketResponse handleCreate(CreateTicketRequest request) {
        return ticketService.createTicket(request);
    }

    public TicketResponse handleGetById(Long id) {
        return ticketService.getTicket(id);
    }

    public TicketResponse handleResolve(Long id) {
        return ticketService.resolveTicket(id);
    }


}

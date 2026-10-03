package ticket_flow.service;

import ticket_flow.dto.CreateTicketRequest;
import ticket_flow.dto.TicketResponse;

public interface TicketService {
    TicketResponse createTicket(CreateTicketRequest request);
    TicketResponse getTicket(Long id);
    TicketResponse resolveTicket(Long id);
}

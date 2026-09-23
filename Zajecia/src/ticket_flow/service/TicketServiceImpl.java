package ticket_flow.service;

import ticket_flow.domain.Ticket;
import ticket_flow.dto.CreateTicketRequest;
import ticket_flow.dto.TicketResponse;
import ticket_flow.repository.TicketRepository;

import java.util.Optional;

public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketResponse createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket(request.title(), request.description());
        Ticket savedTicket = ticketRepository.save(ticket);

        return new TicketResponse(
                savedTicket.getId(),
                savedTicket.getTitle(),
                savedTicket.getStatus()
        );
    }

    @Override
    public TicketResponse getTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Brak ticketu o ID: " + id));
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getStatus()
        );
    }

    @Override
    public TicketResponse resolveTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Brak ticketu o ID: " + id));
        ticket.resolve();
        ticketRepository.save(ticket);

        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getStatus()
        );
    }
}

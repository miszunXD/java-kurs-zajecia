package ticket_flow.repository;

import ticket_flow.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(Long id);
    List<Ticket> findAll();
}

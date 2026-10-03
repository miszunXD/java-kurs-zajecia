package ticket_flow.repository;

import ticket_flow.domain.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTicketRepository implements TicketRepository {
    private final ConcurrentHashMap<Long, Ticket> repository = new ConcurrentHashMap<>();
    private final AtomicLong uniqueId = new AtomicLong();

    @Override
    public Ticket save(Ticket ticket) {
        long id = uniqueId.incrementAndGet();
        ticket.setId(id);
        repository.put(id, ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.ofNullable(repository.get(id));
    }

    @Override
    public List<Ticket> findAll() {
        return repository.values().stream().toList();
    }
}

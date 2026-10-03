package ticket_flow.domain;

public class Ticket {
    private Long id;
    private String title;
    private String description;
    private String status;

    public Ticket(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Tytuł nie może być pusty!");
        }

        this.title = title;
        this.description = description;
        this.status = "NEW";
    }

    public void resolve() {
        if (status.equals("RESOLVED")) {
            throw new IllegalStateException("Ticket jest już rozwiązany!");
        }

        this.status = "RESOLVED";
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

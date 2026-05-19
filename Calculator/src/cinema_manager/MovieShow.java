package cinema_manager;

public class MovieShow {
    private String title;
    private int durationMinutes;
    private double ticketPrice;

    public MovieShow(String title, int durationMinutes, double ticketPrice) {
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.ticketPrice = ticketPrice;
    }

    public MovieShow(String title, int durationMinutes) {
        this(title, durationMinutes, 25.0);
    }

    public static MovieShow createMarathon(String title) {
            return new MovieShow(title, 180, 40.0);
    }

    public String getTitle() {
        return title;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}

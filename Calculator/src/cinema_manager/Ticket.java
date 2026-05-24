package cinema_manager;

public class Ticket {
    //pola instancji
    private final String ticketId;
    private final String movieTitle;
    private double price;

    //pola statyczne
    private static int idCounter = 1000;
    private static double totalRevenue = 0.0;

    //Konstruktor
    public Ticket(String movieTitle, double price) {
        this.ticketId = "TICK-" + (++idCounter);
        this.movieTitle = movieTitle;
        this.price = price;

        totalRevenue += price;
    }

    //Metoda statyczna zwracająca totalRevenue
    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public double getPrice() {
        return price;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    @Override
    public String toString() {
        return "Bilet " + ticketId + " | Film: " + movieTitle + " | Cena: " + price + " zł";
    }
}

package table_res;

import table_res.exceptions.ReservationValidationException;
import table_res.exceptions.RestaurantException;
import table_res.model.Table;
import table_res.service.ReservationService;

public class Main {
    public static void main(String[] args) {

        ReservationService reservationService = new ReservationService();

        // Pomyślna rezerwacja
        reservationService.createReservation("Kosiniak", 5, new Table(14));

        // błędne dane wejściowe : guestCount -2
        try {
            reservationService.createReservation("Kamysz", 11, new Table(5));
        } catch (ReservationValidationException e) {
            System.out.println("Błąd: " + e.getErrorCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        }

        // bookawanie tego samego stolika - wykorzystanie stolika z przykładu 1
        var doubleBookedTable = new Table(4);

        try {
            reservationService.createReservation("Zdanowska", 6, doubleBookedTable);
            reservationService.createReservation("Pustelnik", 8, doubleBookedTable);
        } catch (RestaurantException e) {
            System.out.println("Błąd: " + e.getErrorCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
            System.out.println("Pierwotna przyczyna błędu: " + e.getCause());
        }

    }
}

package table_res.service;

import table_res.model.Table;
import table_res.exceptions.ReservationValidationException;
import table_res.exceptions.RestaurantException;
import table_res.exceptions.TableAlreadyBookedException;

import java.util.Objects;

public class ReservationService {
    public void createReservation(String clientName, int guestsCount, Table table) {
        Objects.requireNonNull(clientName, "Nazwa kleinta nie może być null!");

        if (guestsCount < 1 || guestsCount > 10) {
            throw new ReservationValidationException(
                    "Liczba gości musi być w zakresie 1 - 10",
                    "INVALID_GUEST_COUNT");
        }

        if (table == null) {
            throw new ReservationValidationException(
                    "Stolik nie może być NULL!",
                    "NULL_TABLE_REFERENCE"
            );
        }

        try {
            table.book();
            System.out.println("Pomyślana rezerwacja! Stolik: " + table.getNumber() + " - zarezerowany.");
            System.out.println("Liczba gości: " + guestsCount);
        } catch (TableAlreadyBookedException e) {
            throw new RestaurantException(
                    "Nie udało się utworzyć rezerwacji",
                    "TABLE_BOOK_ERROR",
                    e
            );
        }
    }
}

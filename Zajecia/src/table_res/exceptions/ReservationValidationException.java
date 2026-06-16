package table_res.exceptions;

public class ReservationValidationException extends RestaurantException {
    public ReservationValidationException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

    public ReservationValidationException(String message, String errorCode) {
        super(message, errorCode);
    }
}

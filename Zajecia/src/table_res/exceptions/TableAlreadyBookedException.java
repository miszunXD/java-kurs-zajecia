package table_res.exceptions;

public class TableAlreadyBookedException extends RestaurantException {
    private int tableNumber;

    public TableAlreadyBookedException(int tableNumber) {
        super("Stolik jest już zajęty!", "TABLE_ALREADY_BOOKED");
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }
}

package table_res.model;

import table_res.exceptions.TableAlreadyBookedException;

public class Table {
    private int number;
    private boolean booked;

    public Table(int number) {
        this.booked = false;
        this.number = number;
    }

    public void book() {
        if (booked) {
            throw new TableAlreadyBookedException(number);
        }
        booked = true;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }
}

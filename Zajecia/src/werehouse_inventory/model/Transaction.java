package werehouse_inventory.model;

import java.time.LocalDateTime;

public record Transaction(Product product, int quantity, LocalDateTime timeStamp, String type) {

    public Transaction {
        if (!type.equals("IN") && !type.equals("OUT")) {
            throw new IllegalArgumentException("Typ może być jedynie IN lub OUT!");
        }
    }
}

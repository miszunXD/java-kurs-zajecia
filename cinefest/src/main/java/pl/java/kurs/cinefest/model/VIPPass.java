package pl.java.kurs.cinefest.model;

import java.math.BigDecimal;

public record VIPPass(BigDecimal basePrice, String seat,
                      BigDecimal cateringSurcharge, boolean backstageAccess) implements FestivalTicket{
    @Override
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    @Override
    public String getSeat() {
        return seat;
    }
}

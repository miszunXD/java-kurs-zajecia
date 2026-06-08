package pl.java.kurs.cinefest.model;

import java.math.BigDecimal;
import java.util.List;

public record StandardTicket(BigDecimal basePrice, String seat, List<MovieShow> selectedShows)
implements FestivalTicket{

    @Override
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    @Override
    public String getSeat() {
        return seat;
    }
}

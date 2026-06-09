package pl.java.kurs.cinefest.model;

import java.math.BigDecimal;

public sealed interface FestivalTicket permits StandardTicket, VIPPass {
    BigDecimal getBasePrice();
    String getSeat();


}

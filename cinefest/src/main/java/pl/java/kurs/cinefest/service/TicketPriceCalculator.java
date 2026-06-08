package pl.java.kurs.cinefest.service;

import pl.java.kurs.cinefest.model.MovieShow;
import pl.java.kurs.cinefest.model.FestivalTicket;
import pl.java.kurs.cinefest.model.StandardTicket;
import pl.java.kurs.cinefest.model.VIPPass;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPriceCalculator {
    private static final BigDecimal vatMultiplier = new BigDecimal("1.23");
    private static final BigDecimal threeDCharge = new BigDecimal("15.00");

    public BigDecimal calculateFinalPrice(FestivalTicket ticket) {
        return switch (ticket) {
            case StandardTicket standard -> {
                boolean has3d = standard.selectedShows().stream()
                        .anyMatch(MovieShow::is3d);

                BigDecimal price = standard.basePrice();

                if (has3d) {
                    price = price.add(threeDCharge);
                }
                BigDecimal finalPrice = price.setScale(2, RoundingMode.HALF_UP);

                System.out.printf("""
                        ===BILET===
                        Miejsce: %s
                        Cena biletu: %s zł
                        %n""", standard.getSeat(), finalPrice);

                yield finalPrice;
            }

            case VIPPass vip -> {
                BigDecimal finalPrice = vip.basePrice()
                        .add(vip.cateringSurcharge())
                        .multiply(vatMultiplier)
                        .setScale(2, RoundingMode.HALF_UP);
                System.out.printf("""
                        ===BILET===
                        Miejsce: %s
                        Cena biletu: %s zł
                        %n""", vip.getSeat(), finalPrice);

                yield finalPrice;
            }
        };
    }
}

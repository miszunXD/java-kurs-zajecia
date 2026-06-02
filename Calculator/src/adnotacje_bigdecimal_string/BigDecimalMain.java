package adnotacje_bigdecimal_string;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalMain {
    public static void main(String[] args) {
        BigDecimal accomodation = new BigDecimal("1540.85");
        BigDecimal fuel = new BigDecimal("423.10");
        BigDecimal discount = new BigDecimal("100.00");

        BigDecimal totalCost = accomodation.add(fuel).subtract(discount);
        System.out.println("Łączny koszt: " + totalCost + " zł");

        BigDecimal people = new BigDecimal("4");
        BigDecimal perPerson = totalCost.divide(people, 2, RoundingMode.HALF_UP);
        System.out.println("Koszt per jedna osoba: " + perPerson + " zł");
    }
}

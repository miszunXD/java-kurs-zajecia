package classes;

import java.math.BigDecimal;

public class Electronics extends Product{
    private int warranty;

    public Electronics(String name, double price, String category, int warranty) {
        super(name, price, category);
        this.warranty = warranty;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", gwarancja w miesiącach: " + warranty;
    }
}

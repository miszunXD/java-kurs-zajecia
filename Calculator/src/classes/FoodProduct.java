package classes;

import java.math.BigDecimal;

public class FoodProduct extends Product{
    private String expiryDate;

    public FoodProduct(String name, double price, String category, String expiryDate) {
        super(name, price, category);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", data przydatności do spożycia: " + expiryDate;
    }
}

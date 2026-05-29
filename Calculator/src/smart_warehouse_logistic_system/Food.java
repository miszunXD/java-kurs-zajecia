package smart_warehouse_logistic_system;

public class Food extends Product{
    private String expirationDate;


    public Food(String name, double basePrice, String expirationDate) {
        super(name, basePrice);
        this.expirationDate = expirationDate;
    }

    public Food(Product other, String expirationDate) {
        super(other);
        this.expirationDate = expirationDate;
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * 0.80;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return getName() + " | Cena przed rabatem: " + getBasePrice() + " zł | Gwarancja: "
                + expirationDate + " | Cena po rabacie ze względu na termin ważności: "
                + calculateFinalPrice() + " zł";
    }
}

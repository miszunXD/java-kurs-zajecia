package smart_warehouse_logistic_system;

public class Electronics extends Product implements Shipable{
    private int warrantyMonths;


    public Electronics(String name, double basePrice, int warrantyMonths) {
        super(name, basePrice);
        this.warrantyMonths = warrantyMonths;
    }

    public Electronics(Product other, int warrantyMonths) {
        super(other);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * 1.10;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void ship(String address) {
        System.out.println("Dostarczymy produkt ELEKTRONICZNY pod adres: " + address);
    }

    @Override
    public String toString() {
        return getName() + " | Cena przed rabatem: " + getBasePrice() + " zł | Gwarancja: "
                + warrantyMonths + " | Cena po rabacie: " + calculateFinalPrice() + " zł";
    }
}

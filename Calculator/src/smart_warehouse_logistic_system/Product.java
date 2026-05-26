package smart_warehouse_logistic_system;

public abstract class Product {
    private final String id;
    private String name;
    private double basePrice;

    private static int counter = 0;

    public Product(String name, double basePrice) {
        this.id = "PROD-" + (++counter);
        this.name = name;
        this.basePrice = basePrice;

    }

    public Product(Product other) {
        this.id = "PROD-" + (++counter);
        this.name = other.name;
        this.basePrice = other.basePrice;
    }

    public abstract double calculateFinalPrice();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public static int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + basePrice + " zł";
    }
}

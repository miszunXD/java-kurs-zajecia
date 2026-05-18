package car_management_system;

public class Car extends Vehicle {
    public Car(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void drive() {
        System.out.println("Jadę samochodem spalinowym");
    }
}

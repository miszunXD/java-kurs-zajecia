package car_management_system;

public class Tesla extends Car implements Electric{
    public Tesla(String brand, String model) {
        super(brand, model);
    }

    @Override
    public void drive() {
        System.out.println("Jadę samochodem elektrycznym");
    }

    @Override
    public void charge() {
        System.out.println("Ładowanie akumulatorów Tesli...");
    }


}

package car_management_system;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Tesla("Tesla", "Model Y Performance"));
        vehicles.add(new Car("BMW", "M760Li"));

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
            vehicle.drive();

            if (vehicle instanceof Electric electricVehicle) {
                electricVehicle.charge();
            }

            System.out.println();
        }
    }
}

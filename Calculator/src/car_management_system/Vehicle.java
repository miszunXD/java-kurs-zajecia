package car_management_system;

public abstract class Vehicle {
    private final String brand;
    private final String model;
    private NotificationService notificationService;

    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.notificationService = new NotificationService();
    }

    public abstract void drive();

    @Override
    public String toString() {
        return "Marka pojazdu: " + brand + " | model: " + model
                + " | Serwis: " + notificationService.getServiceName();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }
}

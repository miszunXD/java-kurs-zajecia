package car_management_system;

public class NotificationService {
    private String serviceName;

    public NotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    public NotificationService() {
        this("Standard-Service");
    }

    public String getServiceName() {
        return serviceName;
    }
}

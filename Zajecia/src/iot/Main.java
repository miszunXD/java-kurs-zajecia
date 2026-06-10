package iot;

public class Main {
    public static void main(String[] args) {

        DiagnosticService diagnosticService = new DiagnosticService();

        Building building = new Building(new ControlPanel(new Gateway(null)));
        Building building1 = new Building(new ControlPanel(new Gateway("v2.2.2")));
        Building building2 = new Building(new ControlPanel(new Gateway("v1.1.1")));

        diagnosticService.checkFirmwareStatus(building);
        diagnosticService.checkFirmwareStatus(building1);
        diagnosticService.checkFirmwareStatus(building2);
    }
}

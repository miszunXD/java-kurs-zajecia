package iot;

import java.util.Optional;

public class DiagnosticService {
    public void checkFirmwareStatus(Building building) {
        Optional.of(
                Optional.ofNullable(building)
                        .flatMap(Building::getControlPanel)
                        .flatMap(ControlPanel::getGateway)
                        .flatMap(Gateway::getFirmwareVersion)
                        .filter(version -> !version.isBlank() && version.startsWith("v2."))
                        .orElseGet(() -> {
                            System.out.println("Domyślna wersja...");
                            return "v2.0.0-LTS";
                        })
        ).ifPresentOrElse(
                version -> System.out.println("Poprawna wersja oprogramowania: " + version),
                () -> {
                    throw new IllegalStateException("Błąd krytyczny diagnostyki systemu");
                });
    }
}

package telemetry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Main {

    public static void analyzeTelemetry(Path inputFile, Path outputFile) {

        try(
                Stream<String> lines = Files.lines(inputFile);
                BufferedWriter writer = Files.newBufferedWriter(outputFile);
        ) {
            lines
                    .filter(line -> line.contains(";CRITICAL;"))
                    .map(line -> line.split(";")[1])
                    .forEach( l -> {
                        try {
                            writer.write(l);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        } catch (IOException e) {
            System.err.println("Błąd odczytu plików " + e.getMessage());
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException io) {
                System.out.println("Błąd zapisu: " + io.getMessage());
            } else {
                throw e;
            }
        }
    }

    public static void main(String[] args) {

        Path input = Path.of("telemetry.log");
        Path output = Path.of("critical_alerts_txt");

        String testData = """
            2026-07-14T18:00;ENGINE_TEMP;OK;85.5
            2026-07-14T18:05;HULL_PRESSURE;WARNING;102.1
            2026-07-14T18:10;ENGINE_TEMP;CRITICAL;120.5
            2026-07-14T18:15;SOLAR_PANEL;OK;99.0
            2026-07-14T18:20;OXYGEN_LEVEL;CRITICAL;15.2
            """;
        try {
            Files.writeString(input, testData);
        } catch (IOException e) {
            System.err.println("Błąd operacji na plikach: " + e.getMessage());
        }

        analyzeTelemetry(input, output);
    }
}

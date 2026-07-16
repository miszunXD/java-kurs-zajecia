package space_flow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path missionControl = Path.of("mission_control");
        Path crew = missionControl.resolve("crew_roster.txt");

        List<String> astronauts = List.of(
                "Mirosław Hermaszewski",
                "Donald Tusk",
                "Neil Armstrong",
                "Yuri Gagarin",
                "Buzz Aldrin",
                "Valentina Tereshkova"
        );

        try {
            if (!Files.exists(missionControl)) {
                Files.createDirectories(missionControl);
            }
            Files.write(crew, astronauts);
        } catch (IOException e) {
            System.err.println("Błąd operacji na plikach: " + e.getMessage());
        }

        try {
            List<String> checkCrew = Files.readAllLines(crew);
            checkCrew.stream()
                    .filter(name -> {
                        String[] parts = name.split(" ");
                        String lastName = parts[parts.length - 1];
                        return lastName.length() > 5;
                    })
                    .map(name -> "[ZATWIERDZONY] " + name)
                    .forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Błąd odczytu plików: " + e.getMessage());
        }
    }
}

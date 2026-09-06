package cinema_flow;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<Cinema> cinemas = List.of(
                new Cinema("Warszawa", List.of(
                        new Movie("Odyseja", "Dramat", 170, 9.5),
                        new Movie("Diuna", "SciFi", 190, 8.0),
                        new Movie("Oh Karol", "Komedia", 120, 4.5)
                )),
                new Cinema("Łódź", List.of(
                        new Movie("Odyseja", "Dramat", 170, 9.5),
                        new Movie("Szeregowiec Ryan", "Dramat", 120, 8.0),
                        new Movie("Mandalorian i Grodu", "Komedia", 150, 7.5)
                )),
                new Cinema("Wrocław", List.of(
                        new Movie("8 Pasażer Nostromo", "SciFi", 140, 7.0),
                        new Movie("Szeregowiec Ryan", "Dramat", 120, 8.0),
                        new Movie("Mandalorian i Grodu", "Komedia", 150, 7.5)
                ))
        );

//        Stream<Movie> movieStream = moviesList.stream()
//                .peek(m -> System.out.println("Sprawdzam: " + m.title()));
//
//        boolean isAnyLong = movieStream.anyMatch(m -> m.durationMin() > 180);
//        long totalMovies = movieStream.count();

        /*
        Stream jest operacją jednokrotnego użytku, anyMatch() jest operacją terminalną, count() w ogołe się nie wykona.
        Rzuci wyjątek IllegalStateException.
         */

        //2a
        Set<String> uniqueCategory = cinemas.stream()
                .flatMap(cinema -> cinema.movies().stream())
                .map(Movie::category)
                .collect(Collectors.toSet());
        System.out.println("Lista kategori w kinach: " + uniqueCategory);

        //2b
        OptionalDouble averageRating = cinemas.stream()
                .flatMap(cinema -> cinema.movies().stream())
                .mapToDouble(Movie::rating)
                .average();
        averageRating.ifPresentOrElse(
                average -> System.out.println("Sredni rating filmów w sieci kin: " + average),
                () -> System.out.println("Brak danych")
        );

        //3
        List<Movie> allMovies = cinemas.stream()
                .flatMap(cinema -> cinema.movies().stream())
                .distinct()
                .toList();

        //3A
        Map<String, Long> genreReport = allMovies.stream()
                .collect(Collectors.groupingBy(
                        Movie::category,
                        Collectors.counting()
                ));
        System.out.println("Raport gatunków filmów w sieci kin: " + genreReport);

        //3B
        Map<Boolean, List<String>> hit = allMovies.stream()
                .collect(Collectors.partitioningBy(
                        m -> m.rating() >= 8.0,
                        Collectors.mapping(Movie::title, Collectors.toList())
                ));
        System.out.println("Hity(ocena >= 8.0): " + hit.get(true));
        System.out.println("Pozostałe " + hit.get(false));

        //4
        String sms = allMovies.stream()
                .sorted(Comparator.comparingInt(Movie::durationMin))
                .limit(3)
                .map(Movie::title)
                .reduce((a, b) -> a + " - " + b)
                .orElse("");
        System.out.println("Najkrótsze 3 filmy: " + sms);
    }
}

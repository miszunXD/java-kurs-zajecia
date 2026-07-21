package stream_api;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //1.1 Przypadek A
//    Stream<VideoGame> gamesStream = gamesList.stream().filter(g -> g.price() < 100.0);
//    long cheapGamesCount = gamesStream.count();
//    List<VideoGame> cheapGamesList = gamesStream.collect(Collectors.toList());

    /*
    Stream jest jednokrotnego użycia. Użycie metody count() na strumienie powoduje jego wykonanie
    i zamknięcie. Próba ponownego użycia metody terminalnej toList() rzuci wyjątkiem IllegalStateException.
     */

//        List<VideoGame> cheapGamesList = gamesList.stream()
//                .filter(game -> game.price() < 100.00)
//                .toList();
//        long cheapGamesCount = cheapGamesList.size();

        //1.2 Przypadek B

//        List<String> titles = new ArrayList<>();
//        gamesList.parallelStream()
//                .filter(VideoGame::isMultiplayer)
//                .forEach(g -> titles.add(g.title()));
        /*
        Nie można modyfikować kolekcji przy użyciu parallelStream. Takie działanie może zakończyć
        się utratą danych, brakiem synchronizacji. ArrayList nie jest bezpieczna wielowątkowo.
         */

//        List<String> titles = gamesList.stream()
//                .filter(VideoGame::isMultiplier)
//                .map(VideoGame::title)
//                .toList();

        List<VideoGame> gamesList = List.of(
                new VideoGame("Cyberpunk 2077", "RPG", 149.99, 30000000, false),
                new VideoGame("Call of Duty: Modern Warfare III", "FPS", 279.99, 30000000, true),
                new VideoGame("Counter-Strike 2", "FPS", 0.0, 80000000, true),
                new VideoGame("The Witcher 3", "RPG", 79.99, 60000000, false),
                new VideoGame("Forza Horizon 5", "Racing", 199.99, 40000000, true),
                new VideoGame("Red Dead Redemption 2", "Action", 119.99, 70000000, true)
        );


        //2.1
        Map<Boolean, List<VideoGame>> singleOrMulti = gamesList.stream()
                .collect(Collectors.partitioningBy(VideoGame::isMultiplier));

        System.out.println("Lista gier multiplier: ");
        singleOrMulti.get(true).forEach(game -> System.out.println(game.title()));
        System.out.println("Lista gier singleplayer: ");
        singleOrMulti.get(false).forEach(game -> System.out.println(game.title()));

        //2.2
        Map<String, List<String>> genreCatalogue = gamesList.stream()
                .collect(Collectors.groupingBy(
                        VideoGame::genre,
                        Collectors.mapping(
                                VideoGame::title,
                                Collectors.toList()
                        )
                ));
        System.out.println("Lista gier wg gatunku: ");
        genreCatalogue.forEach((genre, title) -> {
            System.out.println("Gatunek: " + genre);
            System.out.println("Tytuł: " + title);
        });

        //2.3
        DoubleSummaryStatistics priceStats = gamesList.stream()
                .mapToDouble(VideoGame::price)
                .summaryStatistics();

        System.out.println("Najwyższa cena: ");
        System.out.println(priceStats.getMax());

        System.out.println("Srednia cena: ");
        System.out.println(priceStats.getAverage());

        //3.1
        String threeBestsellers = gamesList.stream()
                .sorted(Comparator.comparingDouble(
                        (VideoGame g) -> g.price() * g.copiesSold()
                ).reversed())
                .limit(3)
                .map(VideoGame::title)
                .collect(Collectors.joining(" | "));

        System.out.println("Trzy bestsellery: " + threeBestsellers);

        //3.2
        Map<String, Double> totalRevenuePerGenre = gamesList.stream()
                .collect(Collectors.groupingBy(
                        VideoGame::genre,
                        Collectors.summingDouble(g -> g.copiesSold() * g.price())
                ));

        System.out.println("Totalny przychód per gatunek: ");
        totalRevenuePerGenre.forEach((genre, revenue) ->
                System.out.println(genre + " : " + String.format("%.2f", revenue)));
    }
}

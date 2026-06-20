package treeset_trap;

import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        //Reprodukcja błędu
        TreeSet<Player> players = new TreeSet<>(
                Comparator.comparingInt(Player::score)
                        .reversed());
        addPlayers(players);

        System.out.println("Wypisanie TreeSeta z błędem(brak Marioli)");
        players.forEach(System.out::println);
        //Znika Mariola - ma ten sam score co Andrzej, przez co comparator zwraca 0 i uznaje obiekty za duplikaty
        //finalnie nie wyświetlając Marioli

        //Naprawienie błędu, użycie thenComparing();

        TreeSet<Player> fixedPlayers = new TreeSet<>(
                Comparator.comparingInt(Player::score)
                        .reversed()
                        .thenComparing(Player::nickname)
        );
        addPlayers(fixedPlayers);

        System.out.println("Wypisanie TreeSeta z naprawionym błędem - użycie thenComparing");
        fixedPlayers.forEach(System.out::println);
    }

    private static void addPlayers(TreeSet<Player> players) {
        players.add(new Player("Kamil", 1500));
        players.add(new Player("Andrzej", 1200));
        players.add(new Player("Mariola", 1200));
        players.add(new Player("Sonia", 900));
    }

    /*
    HashSet sprawdza unikalność po hashCode i equals. Obiekty typu Player mają różne hashe - HashSet nie rozpoznaje
    Andrzeja i Marioli jako duplikaty.
    TreeSet sprawdza unikalność po comparatorze - porównując score, obiekt Marioli i Andrzeja jest dla niego duplikatem
    i nie wyświetla jednego z nich (Marioli). Dopiero użycie metody thenComparing i dodanie kolejnego warunku porównania
    powoduje rozróżnienie. Gdyby Mariola przeszła tranzycje i została Andrzejem problem wystąpiłby ponownie przy
    ww. rozwiązaniu pierwotnego problemu.
     */
}

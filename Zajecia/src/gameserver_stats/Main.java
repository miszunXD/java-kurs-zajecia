package gameserver_stats;

public class Main {
    public static void main(String[] args) {

        GameServerManager gameServerManager = new GameServerManager();

        var andrzej = new PlayerKey("UUID-1", "Andrzej");
        var wiesiek = new PlayerKey("UUID-2", "Wiesiek");
        var metyl = new PlayerKey("UUID-3", "Metyl");

        String survival = "Survival-1";
        String skyblock = "SkyBlock-3";

        gameServerManager.joinServer(survival, andrzej);
        gameServerManager.joinServer(survival, wiesiek);
        gameServerManager.joinServer(survival, metyl);

        gameServerManager.joinServer(skyblock, andrzej);
        gameServerManager.joinServer(skyblock, wiesiek);
        gameServerManager.joinServer(skyblock, metyl);

        gameServerManager.addPoints(andrzej, 1000);
        gameServerManager.addPoints(andrzej, 200);
        gameServerManager.addPoints(andrzej, 10000);
        gameServerManager.addPoints(metyl, 1000);
        gameServerManager.addPoints(wiesiek, 2000);

        gameServerManager.printLeaderboards();

        /*
        1/ Kolizja to sytuacja w której do tego samego bucketu trafiają różne klucze, bo ich hashcode
        po przeliczeniu na index daje ten sam wynik.
        Przy 8 elementach w buckecie Java może: 1/ Powiekszyc tablice bucketow,
        najczesciej x2 jako wieloktronosc liczby 2.
        2/ Java zmienia liste na drzewo czerwono czarne (treeification).

        2/Load Factor 0.75 to próg który określa jak bardzo zapełniona jest HashMapa, zanim Java zdecyduje
        że trzeba ją powiekszyć
        Gdy prog jest przekroczony - > rosnie np liczba bucketow z 8 do 16
        Kazdy element jest ponownie przeliczany w hashcode
        Elementy przenosone sa do nowych bucketow
        Stara mapa jest porzucona - do GC.

        Operacja jest kosztowna.

         */
    }
}

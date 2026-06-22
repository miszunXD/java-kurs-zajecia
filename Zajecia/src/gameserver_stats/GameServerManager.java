package gameserver_stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServerManager {
    private final Map<String, List<PlayerKey>> serverLobbies = new HashMap<>();

    private final Map<PlayerKey, Integer> leaderboards = new HashMap<>();


    public void joinServer(String serverName, PlayerKey playerKey) {
        List<PlayerKey> players = serverLobbies.computeIfAbsent(serverName, k -> new ArrayList<>());
        players.add(playerKey);
    }

    public void addPoints(PlayerKey player, int points) {
        leaderboards.merge(player, points, Integer::sum);
    }

    public void printLeaderboards() {
        for (Map.Entry<PlayerKey, Integer> entry : leaderboards.entrySet()) {
            PlayerKey player = entry.getKey();
            int points = entry.getValue();

            System.out.println("Gracz: " + player.userName() + " (UUID: " + player.uuid()
                    + ") -> Punkty: [" + points + "]");
        }
    }
}

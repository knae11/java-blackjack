package blackjack.domain.gamer;

import blackjack.domain.GameTable;
import blackjack.domain.gamer.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(String s, GameTable gameTable) {
        players = new ArrayList<>();
        String[] names = s.split(",");
        for (String name : names) {
            players.add(new Player(name, gameTable));
        }
    }

    public List<Player> getUnmodifiableList() {
        return Collections.unmodifiableList(players);
    }
}

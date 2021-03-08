package blackjack.domain;

import blackjack.utils.CardDeck;

import java.util.ArrayList;
import java.util.Arrays;
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

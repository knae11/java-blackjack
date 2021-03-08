package blackjack;

import blackjack.domain.GameTable;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.utils.FixedCardDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void create() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        Participant player = new Player("john", gameTable);
        assertThat(player.getName()).isEqualTo("john");
    }

    @Test
    void create2() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        Participant player = new Player("sarah", gameTable);
        assertThat(player.getName()).isEqualTo("sarah");
    }

    @Test
    @DisplayName("플레이어가 초기에 카드 두장을 갖고 있는지 확인")
    void create3() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        List<Card> cards = gameTable.initCards();
        Participant player = new Player("sarah", cards);

        List<Card> playerCards = player.getUnmodifiableCards();
        assertThat(playerCards).contains(Card.from("A클로버"), Card.from("2클로버"));
    }

    @Test
    @DisplayName("플레이어에게 카드 추가 지급")
    void add_card() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        List<Card> cards = gameTable.initCards();
        Participant player = new Player("sarah", cards);

        gameTable.giveCard(player);
        assertThat(player.getUnmodifiableCards()).contains(Card.from("A클로버"), Card.from("2클로버"), Card.from("3클로버"));
    }

    @Test
    @DisplayName("플레이어에게 지급된 카드 합계")
    void sum_cards() {
        final GameTable gameTable = new GameTable(new FixedCardDeck());
        List<Card> cards = gameTable.initCards();
        Participant player = new Player("sarah", cards);
        int score = player.sumCards();
        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("결과를 위한 플레이어에게 지급된 카드 합계")
    void sum_cards_for_result() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"), Card.from("6다이아몬드"));
        Participant player = new Player("john", cards);
        int score = player.sumCardsForResult();
        assertThat(score).isEqualTo(17);
    }

    @Test
    @DisplayName("Ace 4장인 경우 지지않는 최대 합계")
    void sum_cards_for_result1() {
        List<Card> cards = Arrays.asList(Card.from("A다이아몬드"),
                Card.from("A다이아몬드"));
        Participant player = new Player("john", cards);
        player.takeCard(Card.from("A다이아몬드"));
        player.takeCard(Card.from("A다이아몬드"));

        int score = player.sumCardsForResult();

        assertThat(score).isEqualTo(14);
    }
}

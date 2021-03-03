package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    @DisplayName("카트 뽑기")
    void pop() {
        CardDeck cardDeck = new FixedCardDeck();
        Card card = cardDeck.pop();
        assertThat(card).isEqualTo(Card.from("A클로버"));
    }

    @Test
    @DisplayName("연속 카트 뽑기")
    void pop2() {
        CardDeck cardDeck = new FixedCardDeck();
        Card card = cardDeck.pop();
        assertThat(card).isEqualTo(Card.from("A클로버"));
        card = cardDeck.pop();
        assertThat(card).isEqualTo(Card.from("2클로버"));
    }

    @Test
    @DisplayName("52번 pop empty 확인")
    void size() {
        CardDeck cardDeck = new FixedCardDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.pop();
        }

        assertThat(cardDeck.isEmpty()).isEqualTo(true);
    }

    @Test
    @DisplayName("51번 pop notEmpty 확인")
    void size2() {
        CardDeck cardDeck = new FixedCardDeck();

        for (int i = 0; i < 51; i++) {
            cardDeck.pop();
        }

        assertThat(cardDeck.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("53번 pop exception 확인")
    void size3() {
        CardDeck cardDeck = new FixedCardDeck();

        for (int i = 0; i < 52; i++) {
            cardDeck.pop();
        }

        assertThatThrownBy(() -> cardDeck.pop()).isInstanceOf(IllegalArgumentException.class);
    }
}

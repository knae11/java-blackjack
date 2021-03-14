package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Collections;
import java.util.List;

public class Player implements Participant {
    private static final String SAMPLE_NAME = "a";
    private final Name name;
    private final Cards cards;
    private final BettingMoney bettingMoney;

    public Player() {
        this(SAMPLE_NAME, new Cards(Collections.emptyList()), new BettingMoney(0));
    }

    public Player(String name) {
        this(name, new Cards(Collections.emptyList()), new BettingMoney(0));
    }

    public Player(String name, String bettingMoney) {
        this(name, new Cards(Collections.emptyList()), new BettingMoney(bettingMoney));
    }

    public Player(long bettingMoney) {
        this(SAMPLE_NAME, new Cards(Collections.emptyList()), new BettingMoney(bettingMoney));
    }

    public Player(String name, Cards cards) {
        this(name, cards, new BettingMoney(0));
    }

    public Player(String name, Cards cards, BettingMoney bettingMoney) {
        this.name = new Name(name);
        this.cards = cards;
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean isAbleToTake() {
        final Score score = sumCards();
        return score.isNotBurst();
    }

    @Override
    public void takeCard(Card card) {
        cards.takeCard(card);
    }

    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public Score sumCards() {
        return cards.sumCards();
    }

    @Override
    public Score sumCardsForResult() {
        return cards.sumCardsForResult();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

}

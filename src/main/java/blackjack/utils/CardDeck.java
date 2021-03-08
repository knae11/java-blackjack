package blackjack.utils;

import blackjack.domain.card.Card;

import blackjack.domain.card.Suits;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public interface CardDeck {
    void assembleWithDenominations(LinkedList<Card> cardsValue, Suits suit);

    Queue<Card> getCards();
//
//    Card pop();
//
//    boolean isEmpty();
//
//    List<Card> initCards();
}

package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.utils.CardDeck;
import blackjack.utils.RandomCardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final CardDeck cardDeck = new RandomCardDeck();
        final Dealer dealer = new Dealer(cardDeck.initCards());
        final Players players = new Players(InputView.getNames(), cardDeck);
        final Participants participants = new Participants(players, dealer);

        OutputView.printParticipantsCards(participants);

        simulate(cardDeck, dealer, players);

        OutputView.printResult(participants);
    }

    private static void simulate(CardDeck cardDeck, Dealer dealer, Players players) {
        for (Player player : players.getUnmodifiableList()) {
            turnForPlayer(cardDeck, player);
        }

        if (dealer.isAvailableToTake()) {
            dealer.takeCard(cardDeck.pop());
            OutputView.printDealerGetCard();
        }
    }

    private static void turnForPlayer(CardDeck cardDeck, Player player) {
        while (player.isAvailableToTake() && InputView.requestOneMoreCard(player.getName())) {
            player.takeCard(cardDeck.pop());
            OutputView.printCards(player);
        }
    }
}

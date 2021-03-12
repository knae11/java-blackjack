package blackjack;

import blackjack.domain.Game;
import blackjack.domain.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {

        final Game game = new Game(InputView.requestNameAndMoney());
        OutputView.printParticipantsCards(game.getProcessDto());

        simulate(game);
        OutputView.printCardsResult(game.getProcessDto());
        OutputView.printOutcome(game.getResultDto());
        OutputView.printProfit(game.getResultDto());
    }

    private static void simulate(Game game) {
        for (Player player : game.getPlayers()) {
            turnForPlayer(game, player);
        }
        game.turnForDealer();
        OutputView.printDealerGetCard();
    }

    private static void turnForPlayer(Game game, Player player) {
        while (!player.isNotAbleToTake() && InputView.requestOneMoreCard(player.getName().toString())) {
            OutputView.printCards(game.turnForPlayer(player));
        }
    }

}

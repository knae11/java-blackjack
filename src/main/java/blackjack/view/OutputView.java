package blackjack.view;

import blackjack.domain.gametable.Outcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Player;
import blackjack.dto.ProcessDto;
import blackjack.dto.ResultDto;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALER_NAME = "딜러";

    private OutputView() {
    }

    public static void printCards(Player player) {
        final String cards = player.getUnmodifiableCards().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", "));

        System.out.println(player.getName() + "카드: " + cards);
    }

    public static void printParticipantsCards(ProcessDto processDto) {
        final String joinedNames = processDto.names().stream()
            .collect(Collectors.joining(", "));

        System.out.printf("\n%s와 %s에게 2장의 나누었습니다.\n", DEALER_NAME, joinedNames);

        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue()));
        }

        System.out.println();
    }

    private static String cardsToString(Cards cards) {
        return cards.getUnmodifiableList().stream()
            .map(Card::getCardName)
            .collect(Collectors.joining(", ", "카드: ", ""));
    }

    public static void printDealerGetCard() {
        System.out.println("\n" + DEALER_NAME +"는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printCardsResult(ProcessDto processDto) {
        for (Entry<String, Cards> value : processDto.cards().entrySet()) {
            System.out.println(value.getKey() + cardsToString(value.getValue())
                + " - 결과: " + value.getValue().sumCardsForResult());
        }
    }

    public static void printOutcome(ResultDto resultDto) {
        System.out.println("\n## 최종 승패");

        printDealerOutcome(resultDto.summarizeFinalOutcomeOfPlayers());
        printPlayersOutcome(resultDto.getPlayersFinalOutcome());
    }

    private static void printPlayersOutcome(Map<String, Outcome> playersFinalOutcome) {
        for (Map.Entry<String, Outcome> entry : playersFinalOutcome.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getWord());
        }
    }

    private static void printDealerOutcome(Map<Outcome, Integer> dealerInfo) {
        System.out.println(DEALER_NAME + ": "
            + dealerInfo.get(Outcome.LOSE) + "승"
            + dealerInfo.get(Outcome.WIN) + "패"
            + dealerInfo.get(Outcome.DRAW) + "무"
        );

    }

    public static void printProfit(ResultDto resultDto) {
        System.out.println();
        System.out.println("## 최종 수익");
        for (Map.Entry<String, BigDecimal> entry : resultDto.summarizePlayerProfit().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

}

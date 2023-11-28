package main;

import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {

    private final int name;
    private StringBuilder output = new StringBuilder();
    private static List<CardDeck> decks;
    private List<Card> cards = new ArrayList<>();
    private List<String> auxCards = new ArrayList<>();

    public Player(int name) {
        this.name = name;
    }

    public int GetName() {
        return name;
    }

    public static List<CardDeck> getDecks() {
        return decks;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getAuxCards() {
        return auxCards;
    }

    public static void setDecks(List<CardDeck> decks) {
        Player.decks = decks;
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
        auxCards.add(String.valueOf(newCard.cardValue()));
    }

    public void removeCard(Card card) {
        cards.remove(card);
        auxCards.remove(String.valueOf(card.cardValue()));
    }

    private boolean checkWin() {
        boolean hasOneCardType = true;
        int firstCard = cards.get(0).cardValue();

        for (Card card : cards) {
            if (card.cardValue() != firstCard) {
                hasOneCardType = false;
                break;
            }
        }
        return hasOneCardType;
    }

    public void writeFinalOutput(int winner) {
        if (winner == name) {
            output.append("player " + name + " wins\n");
            output.append("player " + name + " exits\n");
            output.append("player " + name + " final hand: " + String.join(" ", auxCards) + "\n");
        } else {
            output.append("player " + winner + " has informed player " + name + " that player " + winner + " has won\n");
            output.append("player " + name + " exits\n");
            output.append("player " + name + " hand: " + String.join(" ", auxCards) + "\n");
        }

        String outputFile = "player" + name + "_output.txt";
        CardGame.writeToFile(output, outputFile, true);
    }

    @Override
    public void run() {
        if (decks == null) {
            throw new IllegalStateException("Decks list not set for players");
        }

        int leftDeckIndex = name - 1;
        int rightDeckIndex = name;

        if (rightDeckIndex >= decks.size()) {
            rightDeckIndex = 0;
        }
        CardDeck leftDeck = decks.get(leftDeckIndex);
        CardDeck rightDeck = decks.get(rightDeckIndex);

        //Gets card from left and removes it from deck
        Card drawCard = leftDeck.getCards().get(0);
        addCard(drawCard);
        leftDeck.removeCard(drawCard);

        // Identifies first card which is not preferred card
        // Using -1 as temporary value, code always identifies a discard card due to win-condition checking
        Card discardCard = new Card(-1);

        for (Card card : cards) {
            if (card.cardValue() != name) {
                discardCard = card;

                // Discarding card to right-hand side deck
                removeCard(card);
                rightDeck.addCard(card);
                break;
            }
        }
        int rightDeckName = name + 1;
        if (rightDeckName > decks.size()) {
            rightDeckName = 1;
        }

        // Creating and storing output lines
        String drawOutput = String.format("player %d draws a %d from deck %d\n", name, drawCard.cardValue(), name);
        String discardOutput = String.format("player %d discard a %d to deck %d\n", name, discardCard.cardValue(), rightDeckName);
        String handOutput = String.format("player %d current hand is ", name) + String.join(" ", auxCards) + "\n";

        output.append(drawOutput);
        output.append(discardOutput);
        output.append(handOutput);

        if (checkWin()) {
            CardGame.setWinner(name);
        }
    }
}

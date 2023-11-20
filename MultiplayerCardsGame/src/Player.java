import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final int name;
    private static List<CardDeck> decks; // static field
    private List<Card> cards = new ArrayList<>();
    private List<String> auxCards = new ArrayList<>();

    public Player(int name) {
        this.name = name;
    }

    public int GetName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<String> getAuxCards() {
        return auxCards;
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
        auxCards.add(String.valueOf(newCard.getCardValue()));
    }

    public void removeCard(Card card) {
        cards.remove(card);
        auxCards.remove(String.valueOf(card.getCardValue()));
    }

    public static void setDecks(List<CardDeck> decks) {
        Player.decks = decks;
    }

    @Override
    public void run() {
        if (decks == null) {
            throw new IllegalStateException("Decks list not set for players.");
        }
        System.out.println("Player run()");
        int leftDeckName = name-1;
        int rightDeckName = name;

        if (rightDeckName >= decks.size()) {
            rightDeckName = 1;
        }
        CardDeck leftDeck = decks.get(leftDeckName);
        CardDeck rightDeck = decks.get(rightDeckName);

        //Gets card from left and removes it from deck
        System.out.println(leftDeck.getCards().get(1));
        Card drawCard = leftDeck.getCards().get(0);
        //TODO ERROR
        this.addCard(drawCard);
        leftDeck.removeCard(drawCard);

        // Identifies first card which is not preferred card
        Card discardCard = new Card(-1);

        for (Card card : cards) {
            if (card.getCardValue() != name) {
                discardCard = card;
                this.removeCard(discardCard);
                rightDeck.addCard(card);
                cards.remove(card);
                break;
            }
        }

        // check win condition

        String drawOutput = String.format("player %d draws a %d from deck %d", name, drawCard.getCardValue(), leftDeckName);
        String discardOutput = String.format("player %d draws a %d from deck %d", name, discardCard.getCardValue(), rightDeckName);
        String handOutput = String.format("player %d current hand is", name) + String.join(" ", auxCards);
    }
}

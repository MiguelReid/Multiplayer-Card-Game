import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {

    public static int name;
    List<Card> cards = new ArrayList<>();

    public Player(int name) {
        this.name = name;
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
    }

    public static void exchangeCards(List<CardDeck> decks) {
        int rightDeckName = name + 1;

        if (rightDeckName > decks.size()) {
            rightDeckName = 1;
            CardDeck leftDeck = decks.get(name);
            CardDeck rightDeck = decks.get(rightDeckName);
        }

        // Draw one card and give to their deck
        // It can't be one of their own
    }
}

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {
    private final int name;

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



    public void exchangeCards(List<CardDeck> decks) {
        int leftDeckName = name;
        int rightDeckName = name + 1;

        if (rightDeckName > decks.size()) {
            rightDeckName = 1;
        }
        CardDeck leftDeck = decks.get(leftDeckName); //almost certain will return error as .get is for indexes and name
        // is 1-based not 0 so should be name-1 etc same for below
        CardDeck rightDeck = decks.get(rightDeckName);

        //Gets card from left and removes it from deck
        Card drawCard = leftDeck.getCards().get(0);
        this.addCard(drawCard);
        leftDeck.removeCard(drawCard);

        // Identifies first card which is not preferred card
        Card discardCard = new Card(-1);

        for (Card card : cards){
            if (card.getCardValue() != name) {
                discardCard = card;
                rightDeck.addCard(card);
                cards.remove(card);
                break;
            }
        }

        String drawOutput = String.format("player %d draws a %d from deck %d", name, drawCard.getCardValue(), leftDeckName);
        String discardOutput = String.format("player %d draws a %d from deck %d", name, discardCard.getCardValue(), rightDeckName);
        String handOutput = String.format("player %d current hand is", name) + String.join(" ", auxCards);
    }
}

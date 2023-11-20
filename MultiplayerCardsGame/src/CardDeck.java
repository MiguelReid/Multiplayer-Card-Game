import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    public int name;

    private List<Card> cards = new ArrayList<>();

    public CardDeck(int name){
        this.name = name;
    }

    public List<Card> getCards() {
        System.out.println(cards);
        return cards;
    }

    public void addCard(Card newCard){
        cards.add(newCard);
        System.out.println("Deck " + name + " - now size " + cards.size());
    }

    public void removeCard(Card card) {
        cards.remove(card);
        System.out.println("Deck " + name + " - now size " + cards.size());
    }
}

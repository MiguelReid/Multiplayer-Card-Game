import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    public int name;

    List<Card> cards = new ArrayList<>();

    public CardDeck(int name){
        this.name = name;
    }

    public void addCard(Card newCard){
        cards.add(newCard);
    }
}

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    List<Card> cards = new ArrayList<Card>();

    public CardDeck(){

    }

    public void addCard(Card newCard){
        cards.add(newCard);
    }
}

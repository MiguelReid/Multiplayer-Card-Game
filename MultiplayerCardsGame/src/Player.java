import java.util.ArrayList;
import java.util.List;

public class Player {

    public int name;
    List<Card> cards = new ArrayList<Card>();

    public Player(int name) {
        this.name = name;
    }

    public void addCard(Card newCard) {
        cards.add(newCard);
    }
}

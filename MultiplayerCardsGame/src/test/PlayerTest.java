package test;

import main.Card;
import main.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerTest {

    @Test
    public void getName() {
        var player = new Player(4);
        assertEquals(player.GetName(), 4);
    }

    @Test
    public void getCards() {
        var card = new Card(1);
        var card2 = new Card(2);
        var card3 = new Card(3);
        var card4 = new Card(4);

        var player = new Player(2);
        player.addCard(card);
        player.addCard(card2);
        player.addCard(card3);
        player.addCard(card4);

        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        assertEquals(cards, player.getCards());
    }

    @Test
    public void addCard() {
        var player = new Player(4);
        player.addCard(new Card(1));
        assertNotNull(player.getCards());
    }

    @Test
    public void removeCard() {
        var player = new Player(4);
        var card = new Card(3);
        player.addCard(card);
        player.removeCard(card);
        assertEquals(player.getCards(), new ArrayList<>());
    }
}
package test;

import main.Card;
import main.CardDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    @Test
    void addCard() {
        var deck = new CardDeck(3);
        var card = new Card(1);
        deck.addCard(card);
        assertNotNull(deck);
    }

    @Test
    void removeCard() {
        var deck = new CardDeck(3);
        var card = new Card(1);
        deck.addCard(card);
        assertEquals(1, deck.getCards().size());
        deck.removeCard(card);
        assertEquals(0, deck.getCards().size());
    }

    @Test
    void getCards() {
        var deck = new CardDeck(3);
        var card1 = new Card(1);
        deck.addCard(card1);

        assertEquals(1, deck.getCards().size());
        assertEquals(card1, deck.getCards().get(0));

        var card2 = new Card(2);
        deck.addCard(card2);

        assertEquals(2, deck.getCards().size());
        assertEquals(card2, deck.getCards().get(1));
    }

    @Test
    void getAuxCards() {
        var deck = new CardDeck(3);
        var card1 = new Card(1);
        deck.addCard(card1);

        assertEquals(1, deck.getAuxCards().size());
        assertEquals("1", deck.getAuxCards().get(0));

        var card2 = new Card(2);
        deck.addCard(card2);

        assertEquals(2, deck.getAuxCards().size());
        assertEquals("2", deck.getAuxCards().get(1));
    }
}
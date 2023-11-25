package test;

import main.Card;
import main.CardDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardDeckTest {

    @Test
    void getCards() {
        var deck = new CardDeck(3);
        var card = new Card(1);
        deck.addCard(card);
        assertNotNull(deck.getCards());
    }

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
        deck.removeCard(card);
        assertEquals(deck.getCards().size(), 0);
    }
}
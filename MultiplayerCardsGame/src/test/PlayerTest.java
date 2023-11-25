package test;

import main.Card;
import main.CardDeck;
import main.CardGame;
import main.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /*
    @BeforeEach
    void setUp() {
        // Create decks
        CardDeck leftDeck = new CardDeck(1);
        CardDeck rightDeck = new CardDeck(2);
        decks = new ArrayList<>();
        decks.add(leftDeck);
        decks.add(rightDeck);

        // Set decks for players
        Player.setDecks(decks);

        // Create player
        player = new Player(1);
    }
     */
    /*
    @Test
    void testRun_DrawAndDiscard() {
        // Set up the left deck with a card (value 1)
        Card drawCard = new Card(1);
        decks.get(0).addCard(drawCard);

        // Add a card to the player's hand
        player.addCard(new Card(2)); // Assuming the player has a card with value 2

        // Run the player's turn
        player.run();

        // Verify that the player drew the card
        assertEquals(2, player.getCards().size());
        assertTrue(player.getCards().contains(drawCard));

        // Verify that the left deck is empty after drawing
        assertTrue(decks.get(0).getCards().isEmpty());

        // Run the player's turn again to discard
        player.run();

        // Verify that the player discarded a card to the right deck
        assertEquals(1, player.getCards().size());
        assertTrue(decks.get(1).getCards().contains(new Card(2))); // Verify that card with value 2 is in the right deck
    }
     */

    @Test
    void getName() {
        var player = new Player(4);
        assertEquals(player.GetName(), 4);
    }

    @Test
    void getCards() {
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
    void addCard() {
        var player = new Player(4);
        player.addCard(new Card(1));
        assertNotNull(player.getCards());
    }

    @Test
    void removeCard() {
        var player = new Player(4);
        var card = new Card(3);
        player.addCard(card);
        player.removeCard(card);
        assertEquals(player.getCards(), new ArrayList<>());
    }

    @Test
    void writeToFile() {
        var player = new Player(2);
        //player.writeFinalOutput();
        // Not sure about static methods
    }

    @Test
    void run() {
    }
}
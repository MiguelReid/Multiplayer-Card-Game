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

    private CardDeck leftDeck;
    private CardDeck rightDeck;
    private Player player;

    private List<CardDeck> decks;

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

    @Test
    void testRun_WinCondition() {

        var player1 = new Player(1);
        player1.addCard(new Card(4));
        player1.addCard(new Card(4));
        player1.addCard(new Card(4));
        player1.addCard(new Card(4));

        var player2 = new Player(2);
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));

        var player3 = new Player(3);
        player3.addCard(new Card(1));
        player3.addCard(new Card(1));
        player3.addCard(new Card(1));
        player3.addCard(new Card(1));

        var player4 = new Player(4);
        player4.addCard(new Card(3));
        player4.addCard(new Card(3));
        player4.addCard(new Card(3));
        player4.addCard(new Card(3));

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        CardGame.checkGameWon(players);

        // Verify that the player won
        assertEquals(2, CardGame.getWinner());
    }

    @Test
    void getName() {
    }

    @Test
    void getCards() {
    }

    @Test
    void getAuxCards() {
    }

    @Test
    void addCard() {
    }

    @Test
    void removeCard() {
    }

    @Test
    void setDecks() {
    }

    @Test
    void writeOutput() {
    }

    @Test
    void run() {
    }
}
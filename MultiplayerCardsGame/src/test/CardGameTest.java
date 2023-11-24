package test;

import main.Card;
import main.CardDeck;
import main.CardGame;
import main.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    @Test
    void setWinner() {
        var game = new CardGame();
    }

    @Test
    void initializeGame() {

    }

    @Test
    void generateCards() {
        // Create a mock player and decks
        List<Player> players = List.of(new Player(1), new Player(2));
        Stack<Card> totalCards = new Stack<>();
        List<CardDeck> decks = List.of(new CardDeck(1), new CardDeck(2));

        // Call the method to test
        CardGame.generateCards(2, players, totalCards, decks);

        // Add your assertions here based on the expected behavior
        // For example, you can check if players have received the expected number of cards
        for (Player player : players) {
            assertEquals(4, player.getCards().size());
        }
    }

    @Test
    void checkGameWon() {

    }

    @Test
    void gameLoop() {
    }

    @Test
    void readFile() {
    }
}
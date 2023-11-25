package test;

import main.Card;
import main.CardDeck;
import main.CardGame;
import main.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    /*
    @Test
    void initializeGame() {

    }
     */

    @Test
    void generateCards() {
        // Create a mock player and decks
        List<Player> players = List.of(new Player(1), new Player(2));
        Stack<Card> totalCards = new Stack<>();
        List<CardDeck> decks = List.of(new CardDeck(1), new CardDeck(2));

        CardGame.generateCards(2, "two.txt", players, totalCards, decks);

        for (Player player : players) {
            assertEquals(4, player.getCards().size());
        }

        for (CardDeck deck : decks) {
            assertEquals(4, deck.getCards().size());
        }
    }

    @Test
    void checkGameWon() {
        var player1 = new Player(1);
        player1.addCard(new Card(1));
        player1.addCard(new Card(2));
        player1.addCard(new Card(3));
        player1.addCard(new Card(4));

        var player2 = new Player(2);
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));

        var player3 = new Player(3);
        player3.addCard(new Card(1));
        player3.addCard(new Card(2));
        player3.addCard(new Card(3));
        player3.addCard(new Card(4));

        var player4 = new Player(4);
        player4.addCard(new Card(1));
        player4.addCard(new Card(3));
        player4.addCard(new Card(2));
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

    /*
    @Test
    void gameLoop() {
        var player1 = new Player(1);
        player1.addCard(new Card(1));
        player1.addCard(new Card(2));
        player1.addCard(new Card(3));
        player1.addCard(new Card(4));

        var player2 = new Player(2);
        player2.addCard(new Card(1));
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));
        player2.addCard(new Card(2));

        var player3 = new Player(3);
        player3.addCard(new Card(1));
        player3.addCard(new Card(2));
        player3.addCard(new Card(3));
        player3.addCard(new Card(4));

        var player4 = new Player(4);
        player4.addCard(new Card(1));
        player4.addCard(new Card(3));
        player4.addCard(new Card(2));
        player4.addCard(new Card(3));

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        var deck = new CardDeck(1);
        deck.addCard(new Card(2));
        deck.addCard(new Card(3));
        deck.addCard(new Card(1));
        deck.addCard(new Card(2));

        var deck2 = new CardDeck(1);
        deck2.addCard(new Card(2));
        deck2.addCard(new Card(3));
        deck2.addCard(new Card(1));
        deck2.addCard(new Card(2));

        var deck3 = new CardDeck(1);
        deck3.addCard(new Card(2));
        deck3.addCard(new Card(3));
        deck3.addCard(new Card(1));
        deck3.addCard(new Card(2));

        var deck4 = new CardDeck(1);
        deck4.addCard(new Card(2));
        deck4.addCard(new Card(3));
        deck4.addCard(new Card(1));
        deck4.addCard(new Card(2));

        List<CardDeck> decks = new ArrayList<>();
        decks.add(deck);
        decks.add(deck2);
        decks.add(deck3);
        decks.add(deck4);

        Player.setDecks(decks);

        CardGame.gameLoop(players);
        assertEquals(players.size(), Thread.getAllStackTraces().size());
    }
     */

    @Test
    void readFileNotNull() {
        ArrayList<String> output = CardGame.readFile("MultiplayerCardsGame/four.txt");
        assertNotEquals(output, new ArrayList<>());
    }

    @Test
    void readNonExistigFile() {
        ArrayList<String> notFile = CardGame.readFile("DoesNotExist.txt");
        assertEquals(notFile, new ArrayList<>());
    }
}
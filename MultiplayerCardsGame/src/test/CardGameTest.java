package test;

import main.Card;
import main.CardDeck;
import main.CardGame;
import main.Player;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardGameTest {

    @Test
    public void checkGameWon() {
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

        // Verify that a player won
        assertTrue(CardGame.checkGameWon(players));
    }

    @Test
    public void writeToFile() throws IOException {
        var content = new StringBuilder("This is a test file");
        String pathAux = "MultiplayerCardsGame/src/outputFiles/testingFile.txt";
        Path path = Paths.get(pathAux);

        // We check if the method creates a file, and if the content matches the expected
        CardGame.writeToFile(content, "testingFile.txt", false);
        assertTrue("File exists", Files.exists(path));
        assertEquals(content.toString(), Files.readString(path));

        // Delete the test file after the test
        Files.deleteIfExists(path);
    }

    @Test
    public void readNonExistingFile() {
        ArrayList<String> notFile = CardGame.readFile("DoesNotExist.txt");
        assertEquals(notFile, new ArrayList<>());
    }

    @Test
    public void readFileLineCheck() {
        ArrayList<String> output = CardGame.readFile("MultiplayerCardsGame/src/inputPacks/four.txt");
        assertEquals(32, output.size());
    }

    @Test
    public void generateCards() {
        // Create a mock player and decks
        List<Player> players = List.of(new Player(1), new Player(2));
        List<CardDeck> decks = List.of(new CardDeck(1), new CardDeck(2));
        Player.setDecks(decks);

        CardGame.generateCards(2, "two.txt", players, decks);

        for (Player player : players) {
            assertEquals(4, player.getCards().size());
        }

        for (CardDeck deck : decks) {
            assertEquals(4, deck.getCards().size());
        }

        int numOfOnes = 0;
        int numOfTwos = 0;

        for (CardDeck deck : decks) {
            for (Card card : deck.getCards()) {
                if (card.value() == 1) {
                    numOfOnes++;
                } else if (card.value() == 2) {
                    numOfTwos++;
                }
            }
        }

        for (Player player : players) {
            for (Card card : player.getCards()) {
                if (card.value() == 1) {
                    numOfOnes++;
                } else if (card.value() == 2) {
                    numOfTwos++;
                }
            }
        }

        assertEquals(8, numOfOnes);
        assertEquals(8, numOfTwos);
    }

    @Test
    public void gameLoop() {

        // We start a game with the necessary components
        List<Player> players = List.of(new Player(1), new Player(2), new Player(3), new Player(4), new Player(5), new Player(6));
        List<CardDeck> decks = List.of(new CardDeck(1), new CardDeck(2), new CardDeck(3), new CardDeck(4), new CardDeck(5), new CardDeck(6));

        Player.setDecks(decks);
        CardGame.generateCards(6, "six.txt", players, decks);

        // Check if the 6 players have been writing their moves to their respective files
        assertFileRecentlyModified("MultiplayerCardsGame/src/outputFiles/player1_output.txt");
        assertFileRecentlyModified("MultiplayerCardsGame/src/outputFiles/player2_output.txt");
        assertFileRecentlyModified("MultiplayerCardsGame/src/outputFiles/player3_output.txt");
        assertFileRecentlyModified("MultiplayerCardsGame/src/outputFiles/player4_output.txt");
        assertFileRecentlyModified("MultiplayerCardsGame/src/outputFiles/player5_output.txt");
        assertFileRecentlyModified("MultiplayerCardsGame/src/outputFiles/player6_output.txt");
    }

    private void assertFileRecentlyModified(String filePath) {
        File file = new File(filePath);
        assertTrue("File exists", file.exists());

        long lastModified = file.lastModified();
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - lastModified;

        assertTrue("File has been recently modified", timeDifference < 1000);
    }
}
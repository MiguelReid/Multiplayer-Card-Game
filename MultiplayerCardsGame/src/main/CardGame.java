package main;

import java.io.*;
import java.util.*;

public class CardGame {

    private static int winner;
    String test;

    public static void main(String[] args) {
        initializeGame();
    }

    public static void setWinner(int newWinner) {
        winner = newWinner;
    }

    public static int getWinner(){
        return winner;
    }

    public static void initializeGame() {

        List<Player> players = new ArrayList<>();
        List<CardDeck> decks = new ArrayList<>();

        boolean invalidInput;
        int numPlayers = 0;

        do {
            invalidInput = false;
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Please enter the number of players: ");
            String numPlayersInput = scanner.nextLine();  // Read user input

            // Checking if int, and > 0
            try {
                numPlayers = Integer.parseInt(numPlayersInput);

                if (numPlayers <= 0){
                    System.out.println("Number of players must be > 0");
                    invalidInput = true;
                }

            } catch (NumberFormatException e) {
                System.out.println(numPlayersInput + " is not an integer!");
                invalidInput = true;
            }

        } while (invalidInput);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Please enter the name of the pack to load: ");
        String packFilePath = scanner.nextLine();  // Read user input

        // Creating objects based on input given
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player(i + 1);
            CardDeck deck = new CardDeck(i + 1);
            players.add(player);
            decks.add(deck);
        }

        Player.setDecks(decks);
        // So we don't have deck in the main.Player constructor

        generateCards(numPlayers, packFilePath, players, decks);
    }

    public static void generateCards(int numberPlayers, String packFilePath, List<Player> players, List<CardDeck> decks) {
        ArrayList<String> inputPack = readFile("inputPacks/" + packFilePath); // Reading inputPack

        if (inputPack.isEmpty()) {
            System.out.println("The input pack you entered does not exist.");
            initializeGame();
            return;
        }

        int numCards = inputPack.size();
        int numPlayers = players.size();
        // Checking for 8n lines in inputPack
        boolean incorrectNumCards = !(numCards == 8 * numPlayers);
        if (incorrectNumCards) {
            System.out.println("You entered an invalid inputPack - doesn't contain 8n cards!");
            initializeGame();
            return;
        }

        Stack<Card> totalCards = new Stack<>();
        // Checking each line is a positive int and storing in a stack
        for (String line : inputPack) {
            try {
                Card card = new Card(Integer.parseInt(line));
                totalCards.push(card);

                if (Integer.parseInt(line) <= 0){
                    System.out.println("You entered an invalid inputPack - contains a card with value <= 0!");
                    initializeGame();
                    return;
                }

            } catch (NumberFormatException e) {
                System.out.println("You entered an invalid inputPack - contains a non-integer card!");
                initializeGame();
                return;
            }
        }

        // Distribute cards to players in round-robin fashion
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card nextCard = totalCards.pop();
                players.get(j).addCard(nextCard);
            }
        }

        // Output initial player hands
        for (Player player : players) {
            int playerName = player.GetName();
            List<String> auxCards = player.getAuxCards();

            StringBuilder initialHandOutput = new StringBuilder("player " + playerName + " initial hand " + String.join(" ", auxCards) + "\n");
            writeToFile(initialHandOutput, "player" + playerName + "_output.txt", false);
        }

        // Distribute remaining cards to decks in round-robin fashion
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card nextCard = totalCards.pop();
                decks.get(j).addCard(nextCard);
            }
        }

        if (checkGameWon(players)) { // Initial check of win-condition
            return;
        }
        gameLoop(players);
    }

    // Used for initial checking of win-condition
    // Supports multiple winners
    public static boolean checkGameWon(List<Player> players) {
        List<Player> winners = new ArrayList<>();

        // Checking and storing any winners
        for (Player player : players) {
            boolean hasWon = true;
            int firstCard = player.getCards().get(0).getCardValue();

            for (Card card : player.getCards()) {
                if (card.getCardValue() != firstCard) {
                    hasWon = false;
                    break;
                }
            }

            if (hasWon) {
                winners.add(player);
            }
        }

        for (Player player : winners) {
            System.out.println(player.GetName() + " is a winner!");
        }

        if (winners.isEmpty()) { return false; }

        else { return true; }
    }

    public static void gameLoop(List<Player> players) {
        boolean gameActive = true;

        while (gameActive) {
            List<Thread> playerThreads = new ArrayList<>();

            // Create and start a thread for each player
            for (Player player : players) {
                Thread playerThread = new Thread(player);
                playerThreads.add(playerThread);
                playerThread.start();
            }

            // Wait for all player threads to finish
            for (Thread playerThread : playerThreads) {
                try {
                    playerThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (winner > 0) {
                // Tell each player to write to output file
                for (Player player : players) {
                    player.writeFinalOutput(winner);
                }

                // Write to an output file for each deck
                for (CardDeck deck: Player.getDecks()) {
                    List<String> auxCards = deck.getAuxCards();
                    int deckName = deck.getName();

                    StringBuilder deckOutput = new StringBuilder("deck" + deckName + " contents: " + String.join(" ", auxCards));
                    String filePath = "deck" + deckName + "_output.txt";
                    writeToFile(deckOutput, filePath, false);
                }
                System.out.println("Game won by player " + winner + "!");
                gameActive = false;

                for (Thread thread : playerThreads) {
                    thread.interrupt();
                }
            }
        }
    }

    public static ArrayList<String> readFile(String pathName) {
        File file = new File(pathName);
        BufferedReader reader;
        ArrayList<String> output = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null)
                output.add(line);

        } catch (IOException e) {
            // Returns empty if can't find file
            return new ArrayList<>();
        }
        return output;
    }

    public static void writeToFile(StringBuilder text, String filePath, boolean dontOverwrite) {
        filePath = "outputFiles/" + filePath;

        try {
            FileWriter writer = new FileWriter(filePath, dontOverwrite);
            writer.write(String.valueOf(text));
            writer.close();

        } catch (IOException e) {
            System.out.println("Error occurred writing to file: " + filePath);
            e.printStackTrace();
        }
    }
}
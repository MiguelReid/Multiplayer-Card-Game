package main;

import java.io.*;
import java.util.*;

public class CardGame {

    static List<Player> players = new ArrayList<>();
    static Stack<Card> totalCards = new Stack<>();
    static List<CardDeck> decks = new ArrayList<>();
    static boolean gameActive = true;

    private static int winner = 0;

    public static void main(String[] args) {
        initializeGame();
    }

    public static void setWinner(int newWinner) {
        winner = newWinner;
    }

    private static void initializeGame() {
        String numberPlayers;
        boolean flag;
        int auxNumberPlayers = 0;
        do {
            flag = false;
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("How many players are there?: ");
            numberPlayers = scanner.nextLine();  // Read user input

            try {
                auxNumberPlayers = Integer.parseInt(numberPlayers);
            } catch (NumberFormatException e) {
                System.out.println(numberPlayers + " not an integer!");
                flag = true;
            }
        } while (flag || auxNumberPlayers < 0);

        for (int i = 0; i < auxNumberPlayers; i++) {
            Player player = new Player(i + 1);
            CardDeck deck = new CardDeck(i + 1);
            players.add(player);
            decks.add(deck);
        }

        Player.setDecks(decks);
        // So we don't have deck in the main.Player constructor

        generateCards(auxNumberPlayers);
    }

    private static void generateCards(int numberPlayers) {
        int numCards;
        boolean containsNonInt = false;
        boolean incorrectNumCards;
        int numPlayers = players.size();
        ArrayList<Integer> auxInputPack = new ArrayList<>();

        do {
            //reset auxInputPack
            auxInputPack.clear();

            //read input pack
            ArrayList<String> inputPack = readFile("MultiplayerCardsGame/four.txt");

            //validate
            //check for 8n cards (lines in file)
            numCards = inputPack.size();
            incorrectNumCards = !(numCards == 8 * numPlayers);

            if (incorrectNumCards) {
                System.out.println("You entered an invalid inputPack!");
                continue;
            }

            containsNonInt = false;
            //check each line is a positive int
            for (String line : inputPack) {

                try {
                    auxInputPack.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.out.println("You entered an invalid inputPack!");
                    containsNonInt = true;
                    break;
                }
            }
        } while (incorrectNumCards || containsNonInt);

        for (int c : auxInputPack) {
            Card card = new Card(c);
            totalCards.push(card);
        }

        //assign cards to players
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card nextCard = totalCards.pop();
                players.get(j).addCard(nextCard);
            }
        }

        //output players initial hands
        for (Player player : players) {
            int playerName = player.GetName();
            List<Card> cards = player.getCards();
            List<String> auxCards = player.getAuxCards();

            String initialHandOutput = String.format("player %d initial hand", playerName) + String.join(" ", auxCards);
        }

        //assign remaining cards to decks
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card nextCard = totalCards.pop();
                decks.get(j).addCard(nextCard);
            }
        }

        gameActive = !checkGameWon();
        if (gameActive) {
            gameLoop();
        }
    }

    private static boolean checkGameWon() {
        List<Player> winners;
        winners = getWinners();
        if (winners.isEmpty()) {
            System.out.println("No winner yet");
            return false;
        } else {
            for (Player player : winners) {
                System.out.println(player.GetName() + " is a winner!");
            }
            return true;
        }
    }

    public static List<Player> getWinners() {
        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            boolean flag = true;
            int firstCard = player.getCards().get(0).getCardValue();
            for (Card card : player.getCards()) {
                if (card.getCardValue() != firstCard) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                winners.add(player);
            }
        }
        return winners;
    }

    private static void gameLoop() {
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
                for (Player player : players) {
                    player.writeOutput();
                }
                System.out.println("Game won by player " + winner + "!");
                gameActive = false;

                for (Thread thread : playerThreads) {
                    thread.interrupt();
                }
            }
        }
    }


    private static ArrayList<String> readFile(String pathName) {

        File file = new File(pathName);

        BufferedReader br = null;
        ArrayList<String> output = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));

            String st;

            while ((st = br.readLine()) != null)
                output.add(st);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return output;
    }

    public static void writeToFile(StringBuilder text, String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(String.valueOf(text));
            myWriter.close();
            System.out.println(filePath);
        } catch (IOException e) {
            System.out.println("An error occurred writing to that file.");
            e.printStackTrace();
        }
    }
}
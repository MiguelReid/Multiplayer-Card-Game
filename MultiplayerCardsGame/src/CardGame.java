import java.io.*;
import java.util.*;

public class CardGame {

    static List<Player> players = new ArrayList<Player>();
    static Stack<Card> totalCards = new Stack<Card>();
    static List<CardDeck> decks = new ArrayList<CardDeck>();

    public static void main(String[] args) {
        initializeGame();

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

        generateCards(auxNumberPlayers);
    }

    private static void generateCards(int numberPlayers) {

        /*
        //generate cards
        for (int i = 0; i < numberPlayers; i++) {
            for (int j = 0; j < 8; j++) {
                Card card = new Card(j);
                totalCards.push(card);
            }
        }

        //randomize
        Collections.shuffle(totalCards);
        */

        int numCards;
        boolean containsNonInt = false;
        boolean incorrectNumCards;
        int numPlayers = players.size();
        ArrayList<Integer> auxInputPack = new ArrayList<>();

        do {
            //reset auxInputPack
            auxInputPack.clear();

            //read input pack
            ArrayList<String> inputPack = readFile("MultiplayerCardsGame/src/four.txt");

            //validate
            //check for 8n cards (lines in file)
            numCards = inputPack.size();
            incorrectNumCards = !(numCards == 8*numPlayers);

            if (incorrectNumCards){
                System.out.println("You entered an invalid inputPack!");
                continue;
            }

            containsNonInt = false;
            //check each line is a positive int
            for (String line:inputPack) {

                try {
                    auxInputPack.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
                    System.out.println("You entered an invalid inputPack!");
                    containsNonInt = true;
                    break;
                }
            }
        } while(incorrectNumCards || containsNonInt);

        for (int c:auxInputPack){
            Card card = new Card(c);
            totalCards.push(card);
        }

        //assign cards
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card nextCard = totalCards.pop();
                players.get(j).addCard(nextCard);
            }
        }

        //assign cards
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card nextCard = totalCards.pop();
                decks.get(j).addCard(nextCard);
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
}
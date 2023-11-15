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
            players.add(player);
        }

        generateCards(auxNumberPlayers);
    }

    private static void generateCards(int numberPlayers) {

        //generate cards
        for (int i = 0; i < numberPlayers; i++) {
            for (int j = 0; j < 8; j++) {
                Card card = new Card(j);
                totalCards.push(card);
            }
        }

        //randomize
        Collections.shuffle(totalCards);

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
}
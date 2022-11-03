import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private static int noPlayers;
    public static void main(String[] args) {
        // Get number of players
        Scanner scanner = new Scanner(System.in); // Scanner to read inputs
        System.out.println("Please enter the number of players:");
        noPlayers = Integer.parseInt(scanner.nextLine());

        // Get pack file location
        System.out.println("Please enter location of pack to load:");
        String packFile = scanner.nextLine();
        scanner.close();

        // Read file, check for validity
        ArrayList<Card> pack = readFile(packFile); // All cards in the pack

        // Instantiating card decks
        ArrayList<CardDeck> decks = new ArrayList<>(); // List of decks, equal to no. of players
        for (int i=0; i<noPlayers; i++) {
            decks.add(new CardDeck());
        }

        // Instantiating players
        ArrayList<PlayerThread> players = new ArrayList<>(); // List of players
        for (int i=0; i<noPlayers; i++) {
            CardDeck left = decks.get(i); // Deck to the left and right of each player
            CardDeck right = decks.get((i+1) % noPlayers);
            players.add(new PlayerThread(left, right));
        }

        // Dealing the cards to players
        for (int i=0; i<4; i++) {
            for (int j=0; j<noPlayers; j++) {
                players.get(j).addCard(pack.get(i*4 + j));
            }
        }

        // Dealing cards to decks
        for (int i=0; i<4; i++) {
            for (int j=0; j<noPlayers; j++) {
                decks.get(j).addCard(pack.get(i*4 + j + 4*noPlayers));
            }
        }
    }

    // Method to read pack file and determine validity
    private static ArrayList<Card> readFile(String fileName) {
        while (true) {
            try {
                Scanner reader = new Scanner(new File(fileName)); // Scanner to read the pack file
                ArrayList<Card> lines = new ArrayList<Card>(); // List of Cards in the pack
                while (reader.hasNextLine()) {
                    lines.add(new Card(Integer.parseInt(reader.nextLine())));
                }
                if (lines.size() == (8 * noPlayers)) { // Check length of pack for validity
                    return lines;
                } else {
                    System.out.println("Invalid pack, please enter a valid pack to load:");
                }
            } catch (Exception e) {
                System.out.println("Invalid pack, please enter a valid pack to load:");
            }
        }
    }
}
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    private static int noPlayers;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players:");
        noPlayers = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter location of pack to load:");
        String packFile = scanner.nextLine();
        scanner.close();

        ArrayList<Card> pack = readFile(packFile);

        ArrayList<CardDeck> decks = new ArrayList<>();
        for (int i=0; i<noPlayers; i++) {
            decks.add(new CardDeck());
        }

        ArrayList<PlayerThread> players = new ArrayList<>();
        for (int i=0; i<noPlayers; i++) {
            CardDeck left = decks.get(i);
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

    private static ArrayList<Card> readFile(String fileName) {
        while (true) {
            try {
                Scanner reader = new Scanner(new File(fileName));
                ArrayList<Card> lines = new ArrayList<Card>();
                while (reader.hasNextLine()) {
                    lines.add(new Card(Integer.parseInt(reader.nextLine())));
                }
                if (lines.size() == (8 * noPlayers)) {
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
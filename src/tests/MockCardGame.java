import java.util.ArrayList;
import java.util.Arrays;

public class MockCardGame extends CardGame {
    
    /*
    private static int noPlayers;

    private static CardGame game = new MockCardGame();

    public static void main(String[] args) {

        
        noPlayers = game.getNoPlayers();

        ArrayList<Card> pack = game.getPack();

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
            players.add(new MockPlayerThread(left, right, players));
        }

        // Dealing the cards to players
        for (int i=0; i<4; i++) {
            for (int j=0; j<noPlayers; j++) {
                players.get(j).addCard(pack.get(i*noPlayers + j));
            }
        }

        // Dealing cards to decks
        for (int i=0; i<4; i++) {
            for (int j=0; j<noPlayers; j++) {
                decks.get(j).addCard(pack.get(i*noPlayers + j + 4*noPlayers));
            }
        }

        for (PlayerThread playerThread : players) {
            Thread thread = new Thread(playerThread);
            thread.start();
        }
    }*/

    public int getNoPlayers() { return 4; }

    public ArrayList<Card> getPack() {
        ArrayList<Card> pack = new ArrayList<>(Arrays.asList(
            new Card(1),
            new Card(4),
            new Card(5),
            new Card(1),
            new Card(6),
            new Card(1),
            new Card(3),
            new Card(2),
            new Card(6),
            new Card(7),
            new Card(6),
            new Card(8),
            new Card(3),
            new Card(2),
            new Card(7),
            new Card(1),
            new Card(5),
            new Card(4),
            new Card(2),
            new Card(8),
            new Card(4),
            new Card(6),
            new Card(5),
            new Card(3),
            new Card(7),
            new Card(7),
            new Card(8),
            new Card(3),
            new Card(5),
            new Card(4),
            new Card(8),
            new Card(2)));
        return pack;
    }
}

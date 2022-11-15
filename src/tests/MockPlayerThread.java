import java.util.ArrayList;

public class MockPlayerThread extends PlayerThread {
    private static int noOfPlayers = 0;

    private ArrayList<Card> hand;
    private ArrayList<MockPlayerThread> players;
    private int id;
    private CardDeck leftDeck, rightDeck;
    private volatile boolean ended = false;

    MockPlayerThread(CardDeck left, CardDeck right, ArrayList<MockPlayerThread> players) {
        super(left, right, null);
        this.players = players;
        leftDeck = left;
        rightDeck = right;
        id = ++noOfPlayers;
        hand = new ArrayList<>();
    }

    public void run() {
        return;
    }
}

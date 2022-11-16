import java.util.ArrayList;

public class MockPlayerThread extends PlayerThread {
    MockPlayerThread(CardDeck left, CardDeck right, ArrayList<PlayerThread> players) {
        super(left, right, null);
    }

    private void recordInit() {
        System.out.println("Bleh bleh bleh");
    }
    private void recordMove(Card drawnCard, Card toDiscard) {}
    private void recordEnding(int winnerId) {}
}

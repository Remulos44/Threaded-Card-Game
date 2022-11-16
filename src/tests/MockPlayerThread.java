import java.util.ArrayList;

public class MockPlayerThread extends PlayerThread {
    MockPlayerThread(CardDeck left, CardDeck right, ArrayList<PlayerThread> players) {
        super(left, right, players);
    }

    @Override
    public void recordInit() {
        System.out.println("init");
    }

    @Override
    public void recordMove(Card drawnCard, Card toDiscard) {
        System.out.println("move");
    }

    @Override
    public void recordEnding(int winnerId) {
        System.out.println("end");
    }
}

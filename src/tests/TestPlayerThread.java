import static org.junit.Assert.*;
import org.junit.Test;

public class TestPlayerThread {

    @Test
    public void testaddCard() {
        Card c1 = new Card(1);
        PlayerThread player = new PlayerThread(null, null);
        assertEquals(0, player.showHand().size());
        player.addCard(c1);
        assertEquals(1, player.showHand().size());
    }

    @Test
    public void testRun() {
        Thread t = new Thread(new PlayerThread(null, null));
        t.start();
    }
}
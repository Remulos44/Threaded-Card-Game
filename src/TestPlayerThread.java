import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestPlayerThread {

    @Test
    public void testaddCard() {
        Card c1 = new Card(1);
        PlayerThread player = new PlayerThread(1, null, null, null);
        assertEquals(0, player.showHand().size());
        player.addCard(c1);
        assertEquals(1, player.showHand().size());
    }

    @Test
    public void testInitWin() {
        ArrayList<PlayerThread> players = new ArrayList<>();
        CardDeck left = new CardDeck(1);
        PlayerThread player = new PlayerThread(1, left, null, players);
        players.add(player);
        player.addCard(new Card(1));
        player.addCard(new Card(1));
        player.addCard(new Card(1));
        player.addCard(new Card(1));
        Thread t = new Thread(player);
        t.start();
    }
}
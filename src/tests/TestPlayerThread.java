import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestPlayerThread {

    @Test
    public void testaddCard() {
        Card c1 = new Card(1);
        PlayerThread player = new PlayerThread(null, null, null);
        assertEquals(0, player.showHand().size());
        player.addCard(c1);
        assertEquals(1, player.showHand().size());
    }

    @Test
    public void testInitWin() {
        ArrayList<MockPlayerThread> mockPlayers = new ArrayList<>();
        ArrayList<PlayerThread> players = new ArrayList<>();
        MockPlayerThread mockPlayer = new MockPlayerThread(null, null, players);
        mockPlayers.add(mockPlayer);
        players.add(mockPlayer);
        mockPlayer.addCard(new Card(1));
        mockPlayer.addCard(new Card(1));
        mockPlayer.addCard(new Card(1));
        mockPlayer.addCard(new Card(1));
        Thread t = new Thread(mockPlayer);
        t.start();
    }
}
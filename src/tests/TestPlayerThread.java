package tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.Card;
import main.CardDeck;
import main.PlayerThread;

public class TestPlayerThread {

    @Test
    public void testaddCard() {
        PlayerThread player = new PlayerThread(1, null, null, null, false);
        assertEquals(0, player.showHand().size());
        try {
            Card c1 = new Card(1);
            player.addCard(c1);
        } catch (Exception e){}
        assertEquals(1, player.showHand().size());
    }

    @Test
    public void testInitWin() {
        try {
            ArrayList<PlayerThread> players = new ArrayList<>();
            CardDeck left = new CardDeck(1, false);
            PlayerThread player = new PlayerThread(1, left, null, players, false);
            players.add(player);
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            Thread t = new Thread(player);
            t.start();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetInitString() {
        try {
            ArrayList<PlayerThread> players = new ArrayList<>();
            CardDeck left = new CardDeck(1, false);
            PlayerThread player = new PlayerThread(1, left, null, players, false);
            players.add(player);
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            player.addCard(new Card(1));

            assertEquals("player 1 initial hand: 1 1 1 1", player.getInitString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetMoveString() {
        try {
            ArrayList<PlayerThread> players = new ArrayList<>();
            CardDeck left = new CardDeck(1, false);
            CardDeck right = new CardDeck(2, false);
            PlayerThread player = new PlayerThread(1, left, right, players, false);
            players.add(player);
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            player.addCard(new Card(1));
            player.addCard(new Card(0));

            assertEquals("\nplayer 1 draws a 0 from deck 1"
                + "\nplayer 1 discards a 1 to deck 2"
                + "\nplayer 1 current hand is 1 1 1 0",
                player.getMoveString(new Card(0), new Card(1)));
        } catch (Exception e) {
            fail(e.getMessage());
        }
        
    }

    @Test
    public void testGetEndingString() {
        try {
            ArrayList<PlayerThread> players = new ArrayList<>();
            CardDeck left = new CardDeck(1, false);
            PlayerThread player = new PlayerThread(1, left, null, players, false);
            players.add(player);
            player.addCard(new Card(1));
            player.addCard(new Card(2));
            player.addCard(new Card(3));
            player.addCard(new Card(4));

            assertEquals("\nplayer 1 wins"
                + "\nplayer 1 exits"
                + "\nplayer 1 hand: 1 2 3 4",
                player.getEndingString(1));

            assertEquals("\nplayer 2 has informed player 1 that player 2 has won"
                + "\nplayer 1 exits"
                + "\nplayer 1 hand: 1 2 3 4",
                player.getEndingString(2));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
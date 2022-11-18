package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import main.*;

public class TestCardGame {

    private ArrayList<Card> pack = new ArrayList<>(Arrays.asList(
        new Card(1), new Card(4), new Card(5),
        new Card(1), new Card(6), new Card(1),
        new Card(3), new Card(2), new Card(6),
        new Card(7), new Card(6), new Card(8),
        new Card(3), new Card(2), new Card(7),
        new Card(1), new Card(5), new Card(4),
        new Card(2), new Card(8), new Card(4),
        new Card(6), new Card(5), new Card(3),
        new Card(7), new Card(7), new Card(8),
        new Card(3), new Card(5), new Card(4),
        new Card(8), new Card(2)));

    private ArrayList<Card> badPack = new ArrayList<>(Arrays.asList(new Card(1)));
        
    private ArrayList<CardDeck> decks;

    private ArrayList<PlayerThread> players;

    @Test
    public void testCreateDecks() {

        decks = CardGame.createDecks(4);
    }

    @Test
    public void testCreatePlayers() {

        decks = CardGame.createDecks(4);
        
        players = CardGame.createPlayers(4, decks);
    }

    @Test
    public void testDealOutCards() {
        
        decks = CardGame.createDecks(4);
        players = CardGame.createPlayers(4, decks);

        try {
            CardGame.dealOutCards(players, decks, badPack);
            fail("Should have thrown an error");
        } catch (Exception e) {
            assertTrue(true);
        }

        decks = CardGame.createDecks(4);
        players = CardGame.createPlayers(4, decks);

        for (PlayerThread player : players) {
            assertEquals(0, player.showHand().size());
        }
        for (CardDeck deck : decks) {
            assertEquals(0, deck.showDeck().size());
        }
        
        CardGame.dealOutCards(players, decks, pack);

        assertEquals(decks.size(), players.size());
        for (PlayerThread player : players) {
            assertEquals(4, player.showHand().size());
        }
        for (CardDeck deck : decks) {
            assertEquals(4, deck.showDeck().size());
        }
    }

    @Test
    public void testStartPlaying() {
        
        for (int i = 0; i < 50; i++) {
            decks = CardGame.createDecks(4);
            players = CardGame.createPlayers(4, decks);
            CardGame.dealOutCards(players, decks, pack);
    
            CardGame.startPlaying(players);
            
        }

        
    }
}

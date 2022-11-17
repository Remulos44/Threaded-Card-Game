import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runners.Suite;

import cardgame.Card;
import cardgame.CardDeck;
import cardgame.CardGame;
import cardgame.PlayerThread;

public class TestCardGame {
    @Test
    public void testMain() {
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
        ArrayList<CardDeck> decks = CardGame.createDecks(4);
        ArrayList<PlayerThread> players = CardGame.createPlayers(4, decks);
        CardGame.dealOutCards(players, decks, pack);
        assertEquals(4, players.get(0).showHand().size());
    }
}

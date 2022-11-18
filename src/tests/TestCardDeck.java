package tests;
import static org.junit.Assert.*;
import org.junit.Test;

import main.Card;
import main.CardDeck;

public class TestCardDeck {
    
    @Test
    public void testaddCard() {
        CardDeck deck = new CardDeck(1);
        Card card = new Card(3);
        deck.addCard(card);
        assertEquals(3, deck.drawCard().getValue());
    }

    @Test
    public void testdrawCard() {
        CardDeck deck = new CardDeck(1);
        Card card;
        for (int i = 0; i < 4; i++) {
            card = new Card(1);
            deck.addCard(card);
        }
        boolean works = true;
        try {
            for (int i = 0; i < 4; i++) {
                deck.drawCard();
            }
        } catch (Exception e) {
            works = false;
        }
        assertTrue(works);
        try {
            deck.drawCard();
        } catch (Exception e) {
            works = false;
        }
        assertFalse(works);
    }
}

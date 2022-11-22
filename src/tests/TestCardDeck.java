package tests;
import static org.junit.Assert.*;
import org.junit.Test;

import main.Card;
import main.CardDeck;

public class TestCardDeck {
    
    @Test
    public void testaddCard() {
        CardDeck deck = new CardDeck(1, false);
        Card card;
        try {
            card = new Card(3);
            deck.addCard(card);
        } catch (Exception e) {}
        assertEquals(3, deck.drawCard().getValue());
    }

    @Test
    public void testdrawCard() {
        CardDeck deck = new CardDeck(1, false);
        Card card;
        for (int i = 0; i < 4; i++) {
            try {
                card = new Card(i);
                deck.addCard(card);
            } catch (Exception e) {}
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

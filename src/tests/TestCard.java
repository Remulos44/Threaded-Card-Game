package tests;
import static org.junit.Assert.*;

import org.junit.Test;

import main.Card;

public class TestCard {
    
    @Test
    @SuppressWarnings("unused")
    public void testCard() {
        try {
            Card card = new Card(0);
            assertEquals(0, card.getValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            Card card = new Card(-4);
            fail("Should have thrown an error");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

}

package tests;
import static org.junit.Assert.*;
import org.junit.Test;

import main.Card;

public class TestCard {
    
    @Test
    public void testCard() {
        Card card = new Card(0);
        assertEquals(0, card.getValue());
    }
}

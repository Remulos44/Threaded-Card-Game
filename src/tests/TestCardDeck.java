import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCardDeck {
    
    @Test
    public void testaddCard() {
        CardDeck deck = new CardDeck();
        Card card = new Card(3);
        deck.addCard(card);
        assertEquals(3, deck.drawCard().getValue());
    }
}

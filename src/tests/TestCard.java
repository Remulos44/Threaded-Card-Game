import static org.junit.Assert.*;
import org.junit.Test;

public class TestCard {
    
    @Test
    public void testCard() {
        Card card = new Card(0);
        assertEquals(0, card.getValue());
        assertEquals("Card: 0", card.toString());
    }
}

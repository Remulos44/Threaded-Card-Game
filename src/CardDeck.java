import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    private Queue<Card> cards;

    CardDeck() {
        cards = new LinkedList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Card drawCard() {
        return cards.remove();
    }

    public String toString() {
        return cards.toString();
    }
}
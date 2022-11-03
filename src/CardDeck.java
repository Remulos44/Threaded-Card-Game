import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {
    private Queue<Card> cards;

    // Constructor
    CardDeck() {
        cards = new LinkedList<>();
    }

    public void addCard(Card card) { // Add card when dealing at start of game
        cards.add(card);
    }

    public Card drawCard() { // For when player draws card from deck
        return cards.remove();
    }

    public String toString() {
        return cards.toString();
    }
}
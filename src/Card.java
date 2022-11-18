
public class Card {

    private int val; // The value of the card

    public Card(int val) {
        this.val = val;
    }

    public int getValue() {
        // Returns the card's value
        return val;
    }

    @Deprecated
    public String toString() {
        return "Card: " + val;
    }
}

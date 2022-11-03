public class Card {
    private int val;

    // Constructor
    Card(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
    }

    public String toString() {
        return "Card: " + val;
    }
}

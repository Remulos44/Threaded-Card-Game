import java.util.LinkedList;

public class PlayerThread implements Runnable {
    private static int noOfPlayers = 0;

    private LinkedList<Card> hand;
    private int id;
    private CardDeck leftDeck, rightDeck;

    PlayerThread(CardDeck left, CardDeck right) {
        leftDeck = left;
        rightDeck = right;
        id = ++noOfPlayers;
        hand = new LinkedList<>();
    }

    public void addCard(Card card) { // Add card when dealing at start of game
        hand.add(card);
    }

    public LinkedList<Card> showHand() { // Shows hand of player, used for testing
        return hand;
    }

    public void run() {
        //TODO: Implement
    }

    private void drawCard() { // Draws card from left deck
        hand.add(leftDeck.drawCard());
    }

    private void discard(Card card) { // Discards card to right deck
        rightDeck.addCard(card);
        hand.remove(card);
    }
    /*
    private Card chooseDiscard() {
        //TODO: Choose random card to discard, not including prefered card
    }
    */
}

import java.util.LinkedList;

public class PlayerThread implements Runnable {
    //TODO: Implement
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

    public void run() {
        //TODO: Implement
    }

    private void drawCard() {
        hand.add(leftDeck.drawCard());
    }

    private void discard(Card card) {
        rightDeck.addCard(card);
        hand.remove(card);
    }
    /*
    private Card chooseDiscard() {
        //TODO: Implement
    }
    */

    public LinkedList<Card> showHand() {
        return hand;
    }
}

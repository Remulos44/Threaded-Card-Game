
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class PlayerThread implements Runnable {
    private static int noOfPlayers = 0;

    private LinkedList<Card> hand;
    private int id;
    private CardDeck leftDeck, rightDeck;
    private boolean won = false;

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

    @Override
    public void run() {
        System.out.println("Hello my name is " + id);

        try {
            FileWriter outputWriter = new FileWriter("player"+id+"_output.txt", false);
            String string = "player "+id+" initial hand:";
            for (Card card : hand) {
                string = string.concat(" "+card.getValue());
            }
            outputWriter.write(string);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO: Move this into its own method
        int noOfSameCards = 0;
        int valOfCard = hand.get(0).getValue();
        for (int i=1; i<4; i++) {
            if (hand.get(i).getValue() == valOfCard) {
                noOfSameCards++;
            } else {
                break;
            }
        }
        if (noOfSameCards == 3) {
            //TODO: Implement winning
            won = true;
        }

        try {
            FileWriter outputWriter = new FileWriter("player"+id+"_output.txt", true);
            int i = 0;
            while (!won) {
                if (i++ == 2) won = true;
                Card drawnCard = drawCard();
                //chooseDiscard()
                //discard(...)
    
                String string = "player "+id+" draws a "+drawnCard.getValue()+" from deck "+leftDeck.getId();
                outputWriter.write("\n"+string);
            }
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "ID: " + id + ", cards: " + hand.toString();
    }

    private Card drawCard() { // Draws card from left deck
        Card card = leftDeck.drawCard();
        hand.add(card);
        return card;
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

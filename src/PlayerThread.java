import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class PlayerThread implements Runnable {
    private static int noOfPlayers = 0;

    private ArrayList<Card> hand;
    private int id;
    private CardDeck leftDeck, rightDeck;
    private boolean won = false;

    PlayerThread(CardDeck left, CardDeck right) {
        leftDeck = left;
        rightDeck = right;
        id = ++noOfPlayers;
        hand = new ArrayList<>();
    }

    public void addCard(Card card) { // Add card when dealing at start of game
        hand.add(card);
    }

    public ArrayList<Card> showHand() { // Shows hand of player, used for testing
        return hand;
    }

    @Override
    public synchronized void run() {
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

        won = checkWin();
        if (won) {
            //TODO: Implement Winning
        }

        try {
            FileWriter outputWriter = new FileWriter("player"+id+"_output.txt", true);
            while (!won) {
                won = checkWin();
                Card drawnCard = drawCard();
                Card toDiscard = chooseDiscard();
                discard(toDiscard);

                String string = "player "+id+" draws a "+drawnCard.getValue()+" from deck "+leftDeck.getId();
                outputWriter.write("\n"+string);
    
                string = "player "+id+" discards a "+toDiscard.getValue()+" to deck "+rightDeck.getId();
                outputWriter.write("\n"+string);

                string = "player "+id+" current hand is";
                for (Card card : hand) {
                    string = string.concat(" "+card.getValue());
                }
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

    private boolean checkWin() {
        int noOfSameCards = 0;
        int valOfCard = hand.get(0).getValue();
        for (int i=1; i<4; i++) {
            if (hand.get(i).getValue() == valOfCard) {
                noOfSameCards++;
            } else {
                break;
            }
        }
        return noOfSameCards == 3;
    }
    
    private Card chooseDiscard() {
        ArrayList<Card> discardables = new ArrayList<>();
        for (Card card : hand) {
            if (card.getValue() != id) discardables.add(card);
        }
        Random rand = new Random();
        return discardables.get(rand.nextInt(discardables.size()));
    }
}

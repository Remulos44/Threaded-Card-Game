import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class PlayerThread implements Runnable {
    private static int noOfPlayers = 0;

    private ArrayList<Card> hand;
    private ArrayList<PlayerThread> players;
    private int id;
    private CardDeck leftDeck, rightDeck;
    private volatile boolean ended = false;
    private FileWriter outputWriter;

    PlayerThread(CardDeck left, CardDeck right, ArrayList<PlayerThread> players) {
        this.players = players;
        leftDeck = left;
        rightDeck = right;
        id = ++noOfPlayers;
        hand = new ArrayList<>();
        try {
            outputWriter = new FileWriter("player"+id+"_output.txt", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCard(Card card) { // Add card when dealing at start of game
        hand.add(card);
    }

    public ArrayList<Card> showHand() { // Shows hand of player, used for testing
        return hand;
    }

    @Override
    public void run() {
        //System.out.println("hello my name is " + id);

        try {
            String string = "player "+id+" initial hand:";
            for (Card card : hand) {
                string = string.concat(" "+card.getValue());
            }
            outputWriter.write(string);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            outputWriter = new FileWriter("player"+id+"_output.txt", true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkWin()) {
            for (PlayerThread player : players) {
                player.endGame(id);
            }
        }
        while (!ended) {
            //System.out.println(id + " " + leftDeck.toString() + " " + rightDeck.toString());

            Card drawnCard = drawCard();
            Card toDiscard = chooseDiscard();
            discard(toDiscard);

            try {

                String string = "player "+id+" draws a "+drawnCard.getValue()+" from deck "+leftDeck.getId();
                outputWriter.write("\n"+string);

                string = "player "+id+" discards a "+toDiscard.getValue()+" to deck "+rightDeck.getId();
                outputWriter.write("\n"+string);

                string = "player "+id+" current hand is";
                for (Card card : hand) {
                    string = string.concat(" "+card.getValue());
                }
                outputWriter.write("\n"+string);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (checkWin() && !ended) {
                for (PlayerThread player : players) {
                    player.endGame(id);
                }
            }
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void endGame(int winnerId) {
        ended = true;
        try {
            String string;
            if (winnerId == id) {
                //Won
                System.out.println("player " + id + " wins");
                string = "player " + id + " wins";
                outputWriter.write("\n"+string);
            } else {
                //Lost
                string = "player " + winnerId + " has informed player " + id + " that player " + winnerId + " has won";
                outputWriter.write("\n"+string);
            }
            string = "player " + id + " exits\nplayer " + id + " hand:";
            for (Card card : hand) {
                string = string.concat(" "+card.getValue());
            }
            outputWriter.write("\n"+string);
            outputWriter.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        leftDeck.writeResult();
    }

    public String toString() {
        return "ID: " + id + ", cards: " + hand.toString();
    }

    public void setPlayers(ArrayList<PlayerThread> players) {
        this.players = players;
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

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class PlayerThread implements Runnable {

    private static int noOfPlayers =  0;     // The number of instances of PlayerThread, used to automatically assign IDs

    private ArrayList<Card> hand;            // The player's hand as a list of cards

    private ArrayList<PlayerThread> players; // The list of all players in the game

    private int id;                          // The player's ID

    private CardDeck leftDeck, rightDeck;    // The decks to the player's left and right

    private volatile boolean ended = false;  // Whether the player should stop playing

    private FileWriter outputWriter;         // Writes to the file player[id]_output.txt

    PlayerThread(CardDeck left, CardDeck right, ArrayList<PlayerThread> players) {
        this.players = players;
        leftDeck = left;
        rightDeck = right;
        // Player ID is automatically assigned based on how many have been instantiated
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
    public void run() {
        // Initial state is recorded to player[id]_output.txt
        recordInit();

        if (checkWin()) {
            for (PlayerThread player : players) {
                // If the player has already won when the game begins, tell each player to stop playing and that this player has won
                player.endGame(id);
            }
        }

        // Loop runs every 50ms until a player wins
        while (!ended) {

            // Player draws and discards
            Card drawnCard = drawCard();
            Card toDiscard = chooseDiscard();
            discard(toDiscard);

            // Move is recorded to player[id]_output.txt
            recordMove(drawnCard, toDiscard);

            if (!ended && checkWin()) {
                for (PlayerThread player : players) {
                    // If the player wins on this go, tell each player to stop playing and that this player has won
                    player.endGame(id);
                }
            }
            try {
                // Acts as a clock to synchronise moves between player threads
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void endGame(int winnerId) {
        // Stops game loop from running once this player has been told to stop playing
        ended = true;

        // Records players final state in player[id]_output.txt
        recordEnding(winnerId);
    }

    @Deprecated
    public String toString() {
        return "ID: " + id + ", cards: " + hand.toString();
    }

    private Card drawCard() {
        // Draws card from left deck
        Card card = leftDeck.drawCard();
        // Adds card to the player's hand
        hand.add(card);
        return card;
    }

    private void discard(Card card) {
        // Discards card to right deck
        rightDeck.addCard(card);
        // Removes card from the player's hand
        hand.remove(card);
    }

    private boolean checkWin() {
        int noOfSameCards = 0;
        // Finds the value of the first card in the player's hand
        int valOfCard = hand.get(0).getValue();
        for (int i=1; i<4; i++) {
            // Counts number of cards in the player's hand that have the same value as the first card
            if (hand.get(i).getValue() == valOfCard) {
                noOfSameCards++;
            } else {
                // If a card is found that does not match the first value, there is no point going through the rest of the cards
                break;
            }
        }
        // Return whether all other cards in the player's hand are the same as the first, implying a win
        return noOfSameCards == 3;
    }
    
    private Card chooseDiscard() {
        ArrayList<Card> discardables = new ArrayList<>();
        for (Card card : hand) {
            // Adds each card in the hand to the list of discardable cards if it is not of the preferred number (the player's ID)
            if (card.getValue() != id) discardables.add(card);
        }

        Random rand = new Random();
        // Chooses to discard a random card from the discardables list
        return discardables.get(rand.nextInt(discardables.size()));
    }

    public void recordInit() {
        try {
            // Creates FileWriter that overwrites the file instead of appending to it
            outputWriter = new FileWriter("player"+id+"_output.txt", false);

            String string = "player "+id+" initial hand:";
            for (Card card : hand) {
                string = string.concat(" "+card.getValue());
            }
            // Writes info about the player's initial hand to the file
            outputWriter.write(string);

            // Closes the FileWriter
            outputWriter.close();

            // Creates a new FileWriter that appends to this file instead of overwriting
            outputWriter = new FileWriter("player"+id+"_output.txt", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recordMove(Card drawnCard, Card toDiscard) {
        try {
            String string = "player "+id+" draws a "+drawnCard.getValue()+" from deck "+leftDeck.getId();
            // Writes info about the player's draw from this move to player[id]_output.txt
            outputWriter.write("\n"+string);

            string = "player "+id+" discards a "+toDiscard.getValue()+" to deck "+rightDeck.getId();
            // Writes info about the player's discard from this move to the file
            outputWriter.write("\n"+string);

            string = "player "+id+" current hand is";
            for (Card card : hand) {
                string = string.concat(" "+card.getValue());
            }
            // Writes info about the player's current hand after this move to the file
            outputWriter.write("\n"+string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recordEnding(int winnerId) {
        try {
            String string;
            if (winnerId == id) {
                // If this player has won, print "player [id] wins"
                System.out.println("player " + id + " wins");

                string = "player " + id + " wins";
                // Then, write that this player has won in player[id]_output.txt
                outputWriter.write("\n"+string);
            } else {
                string = "player " + winnerId + " has informed player " + id + " that player " + winnerId + " has won";
                // If this player has not won, write to player[id]_output.txt which player has won
                outputWriter.write("\n"+string);
            }

            string = "player " + id + " exits\nplayer " + id + " hand:";
            for (Card card : hand) {
                string = string.concat(" "+card.getValue());
            }
            // After the game ends, record the player's final hand in player[id]_output.txt
            outputWriter.write("\n"+string);

            // Finally, close the FileWriter
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Tells the deck to the player's left to record its final state
        leftDeck.writeResult();
    }
}

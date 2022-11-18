
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;

public class CardDeck {

    private volatile Queue<Card> cards;
    private int id;

    // Constructor
    public CardDeck(int id) {
        cards = new LinkedList<>();
        this.id = id;
    }

    public synchronized void addCard(Card card) { // Add card when dealing at start of game
        cards.add(card);
    }

    public synchronized Card drawCard() { // For when player draws card from deck
        return cards.remove();
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return cards.toString();
    }

    public void writeResult() {
        try {
            FileWriter outputWriter = new FileWriter("deck"+id+"_output.txt");
            String string = "deck" + id + " contents:";
            for (Card card : cards) {
                string = string.concat(" "+card.getValue());
            }
            outputWriter.write(string);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSize() {
        return cards.size();
    }
}
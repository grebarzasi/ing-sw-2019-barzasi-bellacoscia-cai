package it.polimi.ingsw.cards;

import java.util.*;

/**
 * A deck of cards
 * @author Yuting Cai
 */

public class Deck {

    /**
     * Cards that can be drawn from the deck
     */

    private ArrayList<Card> usable;

    /**
     * Cards that have been discarded, will be shuffled back into the deck when usable is empty
     */

    private ArrayList<Card> discarded;

    /**
     * Default constructor
     * @param cards the list of cards of the deck, all fresh cards are still usable
     */

    public Deck(ArrayList<Card> cards) {
        this.usable = cards;
        this.discarded = new ArrayList<>();
    }

    public Deck() {
        this.usable = new ArrayList<>();
        this.discarded = new ArrayList<>();
    }

    public ArrayList<Card> getUsable() {
        return usable;
    }

    public void setUsable(ArrayList<Card> usable) {
        this.usable = usable;
    }

    public ArrayList<Card> getDiscarded() {
        return discarded;
    }

    public void setDiscarded(ArrayList<Card> discarded) {
        this.discarded = discarded;
    }

    /**
     * Shuffles the deck
     */

    public void shuffle() {
        Collections.shuffle(this.usable);
    }


    /**
     * Pops the top of the deck, if empty the deck is reset
     * @return the popped card
     */
    public Card fetch(){

        Card temp;

        if(!this.usable.isEmpty()){

            temp = this.usable.get(0);
            this.usable.remove(0);

        }else{

            this.reset();
            temp = this.usable.get(0);
            this.usable.remove(0);

        }

        return temp;

    }

    /**
     * Puts the discarded cards back into the usable deck
     */
    public void reset(){

        this.usable.clear();
        this.usable.addAll(this.discarded);

        this.discarded.clear();
        shuffle();

    }

    /**
     * Adds a card into the discarded pile
     * @param c the card to discard
     */
    public void discard(Card c){
        this.discarded.add(c);
    }


}

package it.polimi.ingsw.cards;

import java.util.*;

public class Deck {

    private ArrayList<Card> usable;

    private ArrayList<Card> discarded;



    public Deck(ArrayList<Card> cards) {
        this.usable = cards;
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



    public void shuffle() {
        Collections.shuffle(this.usable);
    }

    public void discard(){
        this.getDiscarded().add(this.getUsable().get(0));
        this.getUsable().remove(0);
    }

    public void reset() {


        while(!this.discarded.isEmpty()){

            this.usable.add(this.discarded.get(0));
            this.discarded.remove(0);

        }

        Collections.shuffle(this.usable);

    }

}

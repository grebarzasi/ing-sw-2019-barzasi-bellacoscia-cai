package it.polimi.ingsw.cards;

import java.util.*;

public class Deck {

    private ArrayList<Card> usable;

    private ArrayList<Card> discarded;



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



    public void shuffle() {
        Collections.shuffle(this.usable);
    }


    //NEW
    public Card fetch(){
        Card temp = this.usable.get(0);
        this.usable.remove(0);
        if(this.usable.isEmpty()){
            reset();
        }
        return temp;
    }

    public void reset(){
        this.usable=this.discarded;
        this.discarded= new ArrayList<>();
        shuffle();
    }

    public void discard(Card c){
        this.discarded.add(c);
    }


}

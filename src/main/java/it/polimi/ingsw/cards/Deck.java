package it.polimi.ingsw.cards;

import it.polimi.ingsw.cards.Card;

import java.util.*;

public class Deck {

    private ArrayList<Card> usableCards;

    private ArrayList<Card> discardCards;

    public Deck() {
    }

    public ArrayList<Card> getUsableCards() {
        return usableCards;
    }

    public void setUsableCards(ArrayList<Card> usableCards) {
        this.usableCards = usableCards;
    }

    public ArrayList<Card> getDiscardCards() {
        return discardCards;
    }

    public void setDiscardCards(ArrayList<Card> discardCards) {
        this.discardCards = discardCards;
    }

    public void shuffle() {
    }

    public void reset() {
    }

}


package it.polimi.ingsw.cards;

import it.polimi.ingsw.Figure;


public abstract class Card {

    private Figure owner;

    public Card() {
    }

    public Figure getOwner() {
        return owner;
    }

    public void setOwner(Figure owner) {
        this.owner = owner;
    }

}
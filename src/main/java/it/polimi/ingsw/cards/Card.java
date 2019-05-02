
package it.polimi.ingsw.cards;

import it.polimi.ingsw.Figure;


public abstract class Card {

    private Figure owner;

    public Figure getOwner() {
        return owner;
    }

    public void setOwner(Figure owner) {
        this.owner = owner;
    }

    public abstract void fetch();

    public Card() {
    }


}

package it.polimi.ingsw.cards;

import it.polimi.ingsw.Player;


public abstract class Card {

    private Player owner;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public abstract void fetch();

}
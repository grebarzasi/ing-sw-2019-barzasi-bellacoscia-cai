package it.polimi.ingsw.cards;

import java.util.ArrayList;


/**
 * A deck of weapons
 * differ from simple cards one because is limited and never re-shuffled when is finished.
 * you cannot discard a weapon, only replace it with one from an armory
 */

public class WeaponDeck extends Deck {

    /**
     * Default constructor
     * @param w the weapons to add to the deck
     */
    public WeaponDeck(ArrayList<Card> w){
        super(w);
    }

    @Override
    public Card fetch() {

        if(super.getUsable().isEmpty()){
            return null;
        }
        Card temp = super.getUsable().get(0);
        super.getUsable().remove(0);
        return temp;
    }
}

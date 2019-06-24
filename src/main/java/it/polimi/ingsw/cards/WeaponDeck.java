package it.polimi.ingsw.cards;

import java.util.ArrayList;

public class WeaponDeck extends Deck {

    public WeaponDeck(ArrayList<Card> w){
        super(w);
    }

    @Override
    public void shuffle() {
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

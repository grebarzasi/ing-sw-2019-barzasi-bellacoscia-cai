package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.PlayerBoard;
import it.polimi.ingsw.cards.Ammo;

/**
 * The type Tagback grenade.
 * @author Carlo Bellacoscia
 */
public class TagbackGrenade extends PowerUp {


    /**
     * Instantiates a new Tagback grenade.
     *
     * @param ammoOnDiscard the ammo on discard
     * @param name          the name
     */
    public TagbackGrenade(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }


}
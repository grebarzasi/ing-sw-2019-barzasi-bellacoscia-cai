package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;

/**
 * The type Power up.
 *
 * @author Carlo Bellacoscia
 */
public abstract class PowerUp extends Card {

    private Ammo ammoOnDiscard;

    private String name;


    public abstract void effect();


    public PowerUp(Ammo ammoOnDiscard, String name) {
        this.ammoOnDiscard = ammoOnDiscard;
        this.name = name;
    }

    public Ammo getAmmoOnDiscard() {
        return ammoOnDiscard;
    }

    public void setAmmoOnDiscard(Ammo ammoOnDiscard) {
        this.ammoOnDiscard = ammoOnDiscard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    ;
}

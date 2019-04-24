package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;

/**
 *
 * @author Carlo Bellacoscia
 */

public abstract class PowerUp extends Card {

    private Ammo ammoOnDiscard;

    private String name;


    public abstract void effect();
    public abstract void effect(Player p);
    public abstract void effect(Player owner, Player target);
    public abstract void effect(Square s, Player p);


    public PowerUp(Ammo ammoOnDiscard, String name) {
        this.ammoOnDiscard = ammoOnDiscard;
        this.name = name;
    }

    public PowerUp() {
        super();
    }

    /**
     * All setter and getter.
     */

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


    public void fetch(){}
}

package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;
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

    /**
     * Effect.
     *
     * @param p the p
     */
    public abstract void effect(Player p);

    /**
     * Effect.
     *
     * @param owner  the owner
     * @param target the target
     */
    public abstract void effect(Player owner, Player target);

    /**
     * Effect.
     *
     * @param s the s
     * @param p the p
     */
    public abstract void effect(Square s, Player p);


    /**
     * Instantiates a new Power up.
     *
     * @param ammoOnDiscard the ammo on discard
     * @param name          the name
     */
    public PowerUp(Ammo ammoOnDiscard, String name) {
        this.ammoOnDiscard = ammoOnDiscard;
        this.name = name;
    }

    /**
     * All setter and getter.
     *
     * @return the ammo on discard
     */
    public Ammo getAmmoOnDiscard() {
        return ammoOnDiscard;
    }

    /**
     * Sets ammo on discard.
     *
     * @param ammoOnDiscard the ammo on discard
     */
    public void setAmmoOnDiscard(Ammo ammoOnDiscard) {
        this.ammoOnDiscard = ammoOnDiscard;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    public void fetch(){}
}

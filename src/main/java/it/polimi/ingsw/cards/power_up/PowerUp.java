package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;

/**
 * The type Power up.
 *
 * @author Carlo Bellacoscia
 */

public class PowerUp extends Card {


    public static final String TELEPORTER = "Teletrasporto";
    public static final String NEWTON = "Raggio Cinetico";
    public static final String TARGETING_SCOPE = "Mirino";
    public static final String TAGBACK_GRENADE = "Granata Venom";

    /**
     * The ammo that the player can gain discarding this powerup
     */
    private Ammo ammoOnDiscard;

    /**
     * Type of PowerUp
     */
    private String name;

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



}

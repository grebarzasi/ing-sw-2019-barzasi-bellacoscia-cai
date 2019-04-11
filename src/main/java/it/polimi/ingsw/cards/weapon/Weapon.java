package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;

import java.util.*;

/**
 * Represent the weapon in our model.
 * Uses {@link Effect} class to implement effects information (e.g cost, damage, aiming...)
 *
 * @author Gregorio Barzasi
 */


public abstract class Weapon implements Card {

    private String name;
    private Ammo chamber;

    private boolean loaded;

    private Effect basicEffect;
    private Effect addOneEffect;
    private Effect addTwoEffect;
    private Effect alternativeEffect;

    /**
     * easy way for implementing the "move before or after" other effect.
     */
    private Effect extraMove;

    /**
     * used in some effect in order to act as a chain.
     */
    private Player lastHit;


    public Weapon() {
    }

    public abstract void basic();

    public abstract void alternative();

    public abstract void additionalOne();

    public abstract void additionalTwo();

    public void buildWeapon() {
    }

    public void fetch() {

    }
}
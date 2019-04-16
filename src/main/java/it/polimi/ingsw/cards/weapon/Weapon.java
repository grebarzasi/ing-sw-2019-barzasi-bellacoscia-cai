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


public class Weapon extends Card {

    private String name;
    private Ammo chamber;
    private Player owner=null;
    private boolean loaded=false;

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


    public Weapon(String name,Ammo chamber) {
        this.name=name;
        this.chamber=chamber;
    }

    public void basic(){}

    public void alternative(){}

    public void additionalOne(){}

    public void additionalTwo(){}

    public String getName() {
        return name;
    }

    public Ammo getChamber() {
        return chamber;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Effect getBasicEffect() {
        return basicEffect;
    }

    public void setBasicEffect(Effect basicEffect) {
        this.basicEffect = basicEffect;
    }

    public Effect getAddOneEffect() {
        return addOneEffect;
    }

    public void setAddOneEffect(Effect addOneEffect) {
        this.addOneEffect = addOneEffect;
    }

    public Effect getAddTwoEffect() {
        return addTwoEffect;
    }

    public void setAddTwoEffect(Effect addTwoEffect) {
        this.addTwoEffect = addTwoEffect;
    }

    public Effect getAlternativeEffect() {
        return alternativeEffect;
    }

    public void setAlternativeEffect(Effect alternativeEffect) {
        this.alternativeEffect = alternativeEffect;
    }

    public Effect getExtraMove() {
        return extraMove;
    }

    public void setExtraMove(Effect extraMove) {
        this.extraMove = extraMove;
    }

    public Player getLastHit() {
        return lastHit;
    }

    public void setLastHit(Player lastHit) {
        this.lastHit = lastHit;
    }

    public Player getOwner() {
        return owner;
    }

    public void fetch() {
        //SET OWNER ON FETCH!
    }
}
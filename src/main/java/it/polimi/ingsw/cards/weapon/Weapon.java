package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;
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
    private boolean loaded=false;

    private Preferences pref = new Preferences();

    private Effect basicEffect;
    private Effect addOneEffect;
    private Effect addTwoEffect;
    private Effect alternativeEffect;

    /**
     * easy way for the "move before or after" other basic effect.
     *
     */
    private Effect extraMove;
    private boolean beforeBasic=true;

    /**
     * used in some effect in order to act as a chain.
     */
    private Figure lastHit;

    public Weapon(){};


    public Weapon(String name,Ammo chamber) {
        this.name=name;
        this.chamber=chamber;
    }

    /**
     * reloads the weapon, removes the cost from owner's ammo inventory
     *
     * @return true if the cost can be covered, false otherwise
     */
    public boolean reload() {

        if (covers(this.getOwner().getPersonalBoard().getAmmoInventory(), this.getBasicEffect().getCost())) {
            this.getOwner().getPersonalBoard().removeAmmo(this.getBasicEffect().getCost());
            this.setLoaded(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if an ammo covers the reload cost of another ammo object
     *
     * @param a available ammunition
     * @param b ammunition cost
     * @return true if it the cost can be covered, false otherwise
     */
    public boolean covers(Ammo a, Ammo b) {

        if (a.getBlue() - b.getBlue() < 0 || a.getRed() - b.getRed() < 0 || a.getYellow() - b.getYellow() < 0) {
            return false;
        }

        return true;
    }

    public void basic(){}

    public void alternative(){}

    public void additionalOne(){}

    public void additionalTwo(){}

    public void extraMove(){}

    public Set<Effect> getUsableEff(){
        return null;
    }


    public void use(){
        this.getOwner().getModel().askUseEffect(getUsableEff());
    }

    //setters&getters
    public void setBeforeBasicExtra(boolean b){
        this.beforeBasic=false;
    }

    public String getName() {
        return name;
    }

    public Preferences getPreferences() {
        return pref;
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

    public Figure getLastHit() {
        return lastHit;
    }

    public void setLastHit(Figure lastHit) {
        this.lastHit = lastHit;
    }

    public Player getOwner() {
        return super.getOwner();
    }


}
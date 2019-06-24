package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.weapon.aiming.AimAskPlayer;
import it.polimi.ingsw.cards.weapon.aiming.AimDirection;

import java.util.*;

/**
 * Represent the weapon in our model.
 * Uses {@link Effect} class to implement effects information (e.g cost, damage, aiming...)
 *
 * @author Gregorio Barzasi
 */


public class Weapon extends Card {

    private Player owner;
    private String name;
    private Ammo chamber;
    private Ammo reloadCost;
    private boolean isLoaded;
    private int indexTemp;
    private AimDirection directionTemp;
    private AimAskPlayer askTemp;
    private MoveTarget moveTemp;


    private Effect basicEffect;
    private Effect addOneEffect;
    private Effect addTwoEffect;
    private Effect alternativeEffect;

    /**
     * easy way for the "move before or after" other basic effect.
     *
     */
    private Effect extraMove;
    private boolean beforeBasic;

    /**
     * used in some effect in order to act as a chain.
     */
    private Figure lastHit;
    private Figure lastMoved;
    private Set<Figure> damaged;


    public Weapon(){
        this.isLoaded = true;
    }


    public Weapon(String name,Ammo chamber) {
        this.name=name;
        this.chamber=chamber;
        this.isLoaded = true;
        this.directionTemp=null;
        this.askTemp=null;
        this.damaged= new HashSet<>();
        this.beforeBasic=true;
    }

    /**
     * reloads the weapon, removes the cost from owner's ammo inventory
     *
     * @return true if the cost can be covered, false otherwise
     */
    public boolean reload() {

        if (this.getOwner().getPersonalBoard().getAmmoInventory().covers(reloadCost)) {
            this.getOwner().getPersonalBoard().removeAmmo(reloadCost);
            this.setLoaded(true);
            return true;
        } else {
            return false;
        }
    }




    public Set<Effect> getUsableEff(){
        Set<Effect> usable=new HashSet<>();

        if(basicEffect!=null&&!basicEffect.isUsed()&&(alternativeEffect==null||!alternativeEffect.isUsed()))
            usable.add(basicEffect);
        if(alternativeEffect!=null&&!alternativeEffect.isUsed()&&!basicEffect.isUsed())
            usable.add(alternativeEffect);
        if(addOneEffect!=null&&!addOneEffect.isUsed()&&basicEffect.isUsed())
            usable.add(addOneEffect);
        if(addTwoEffect!=null&&!addTwoEffect.isUsed()&&basicEffect.isUsed())
            usable.add(addTwoEffect);
        if((extraMove!=null&&!extraMove.isUsed())&&(beforeBasic||basicEffect.isUsed()))
            usable.add(extraMove);
        return usable;
    }

    public void resetWeapon() {

        directionTemp=null;
        askTemp=null;
        moveTemp=null;
        damaged.clear();

        if(basicEffect!=null)
            basicEffect.resetEffect();
        if(alternativeEffect!=null)
            alternativeEffect.resetEffect();
        if(addOneEffect!=null)
            addOneEffect.resetEffect();
        if(addTwoEffect!=null)
            addTwoEffect.resetEffect();
        if(extraMove!=null)
            extraMove.resetEffect();
    }

    //setters&getters
    public void setBeforeBasicExtra(boolean b){
        this.beforeBasic=false;
    }

    public String getName() {
        return name;
    }

    public Ammo getChamber() {
        return chamber;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        this.isLoaded = loaded;
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
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChamber(Ammo chamber) {
        this.chamber = chamber;
    }


    public boolean isBeforeBasic() {
        return beforeBasic;
    }

    public void setBeforeBasic(boolean beforeBasic) {
        this.beforeBasic = beforeBasic;
    }

    public int getIndexTemp() {
        return indexTemp;
    }

    public void setIndexTemp(int indexTemp) {
        this.indexTemp = indexTemp;
    }


    public AimAskPlayer getAskTemp() {
        return askTemp;
    }

    public void setAskTemp(AimAskPlayer askTemp) {
        this.askTemp = askTemp;
    }

    public AimDirection getDirectionTemp() {
        return directionTemp;
    }

    public void setDirectionTemp(AimDirection directionTemp) {
        this.directionTemp = directionTemp;
    }

    public MoveTarget getMoveTemp() {
        return moveTemp;
    }

    public void setMoveTemp(MoveTarget moveTemp) {
        this.moveTemp = moveTemp;
    }

    public Ammo getReloadCost() {
        return reloadCost;
    }

    public void setReloadCost(Ammo reloadCost) {
        this.reloadCost = reloadCost;
    }

    public Figure getLastMoved() {
        return lastMoved;
    }

    public void setLastMoved(Figure lastMoved) {
        this.lastMoved = lastMoved;
    }

    public Set<Figure> getDamaged() {
        return damaged;
    }

    public void setDamaged(Set<Figure> damaged) {
        this.damaged = damaged;
    }

}
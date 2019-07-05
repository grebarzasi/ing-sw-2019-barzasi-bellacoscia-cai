package it.polimi.ingsw.model.cards.weapon;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.Ammo;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.weapon.aiming.AimAskPlayer;
import it.polimi.ingsw.model.cards.weapon.aiming.AimDirection;
import it.polimi.ingsw.model.cards.weapon.aiming.AimSquare;

import java.util.*;

/**
 * Represent the weapon in our model.
 * Uses {@link Effect} class to implement effects information (e.g cost, damage, aiming...)
 *
 * @author Gregorio Barzasi
 */


public class Weapon implements Card {
    /**
     * the owner of the weapon
     */
    private Player owner;
    /**
     * the name of the weapon
     */
    private String name;
    /**
     * the already loaded ammo
     */
    private Ammo chamber;
    /**
     * the reload cost of the weapon
     */
    private Ammo reloadCost;
    /**
     * indicates that your weapon is loaded
     */
    private boolean isLoaded;


    /**
     * if not null indicates that a filter need an action from a player to determine a direction
     */
    private AimDirection directionTemp;
    /**
     * if not null indicates that a filter need an action from a player to determine a set of target
     */
    private AimAskPlayer askTemp;
    /**
     * if not null indicates that a filter need an action from a player to determine a destination
     */
    private MoveTarget moveTemp;
    /**
     * if not null indicates that a filter need an action from a player to determine a square target
     */
    private AimSquare squareTemp;

    /**
     * indicates that additional effect has to be executed in a order
     * */
    private boolean orderedAdd;
    /**
     * basic effect
     */
    private Effect basicEffect;
    /**
     * additional effect
     */
    private Effect addOneEffect;
    /**
     * second additional effect
     */
    private Effect addTwoEffect;
    /**
     * alternative effect
     */
    private Effect alternativeEffect;

    /**
     * easy way for the "move before or after" other basic effect.
     *
     */
    private Effect extraMove;
    /**
     * indicates that the extra move can be done at any time
     */
    private boolean beforeBasic;

    /**
     * save the last target hit
     */
    private Figure lastHit;
    /**
     * save the last target moved
     */
    private Figure lastMoved;
    /**
     * save all damaged targets
     */
    private Set<Figure> damaged;
    /**
     * save the target selected by aim square
     */
    private Set<Figure> prevSelected;
    /**
     * save the selected square
     */
    private Square selected;


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
        this.prevSelected= new HashSet<>();
        this.beforeBasic=true;
        this.orderedAdd=false;
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


    /**
     * @return a set of usable effect in any given time. used by {@link it.polimi.ingsw.controller.Shooting}
     */

    public Set<Effect> getUsableEff(){
        Set<Effect> usable=new HashSet<>();

        if(basicEffect!=null&&!basicEffect.isUsed()&&(alternativeEffect==null||!alternativeEffect.isUsed()))
            usable.add(basicEffect);
        if(alternativeEffect!=null&&!alternativeEffect.isUsed()&&(basicEffect!=null&&!basicEffect.isUsed()))
            usable.add(alternativeEffect);
        if(addOneEffect!=null&&!addOneEffect.isUsed()&&(basicEffect!=null&&basicEffect.isUsed()))
                usable.add(addOneEffect);
        if (addTwoEffect != null && !addTwoEffect.isUsed() && (basicEffect != null && basicEffect.isUsed()) && (!orderedAdd || (addOneEffect != null && addOneEffect.isUsed())))
            usable.add(addTwoEffect);
        if((extraMove!=null&&!extraMove.isUsed())&&(beforeBasic||(basicEffect!=null&&basicEffect.isUsed())))
            usable.add(extraMove);
        return usable;
    }

    /**
     * reset all weapon and his effects to original state.
     */
    public void resetWeapon() {

        directionTemp=null;
        askTemp=null;
        moveTemp=null;
        squareTemp=null;
        selected=null;
        prevSelected.clear();
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
        this.beforeBasic=b;
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

    public void setOrderedAdd(boolean orderedAdd) {
        this.orderedAdd = orderedAdd;
    }

    public Set<Figure> getPrevSelected() {
        return prevSelected;
    }

    public void setPrevSelected(Set<Figure> prevSelected) {
        this.prevSelected = prevSelected;
    }

    public Square getSelected() {
        return selected;
    }

    public void setSelected(Square selected) {
        this.selected = selected;
    }

    public AimSquare getSquareTemp() {
        return squareTemp;
    }

    public void setSquareTemp(AimSquare squareTemp) {
        this.squareTemp = squareTemp;
    }
}
package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.Ammo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *Used by {@link Weapon} class to represent effects.
 * Works mainly using a combination of {@link SubEffect}
 * @author Gregorio Barzasi
 */
public class Effect {
    private Ammo cost;
    private ArrayList<SubEffect> effectList;



    private Set<Figure> targetHitSet;
    private boolean used;
    private Weapon myWeapon;

    public Effect(){};

    public Effect(Ammo cost,ArrayList<SubEffect> effectList){
        this.cost=cost;
        this.effectList=effectList;
        this.targetHitSet= new HashSet<>();
        this.used= false;
    }

    public Ammo getCost() {
        return cost;
    }

    public void setMyWeapon(Weapon w){
        this.myWeapon=w;
    }

    public ArrayList<SubEffect> getEffectList() {
        return effectList;
    }

    public void setTargetHitSet(Set<Figure> targetHitSet) {
        this.targetHitSet = targetHitSet;
    }

    public Set<Figure> getTargetHitSet() {
        return targetHitSet;
    }

    public void executeEffect() {
        targetHitSet=myWeapon.getOwner().allFigures();
        for (SubEffect e : effectList)
            targetHitSet = e.applyEffect(myWeapon, targetHitSet);
    }

}

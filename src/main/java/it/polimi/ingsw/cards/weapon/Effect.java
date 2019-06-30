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
    private String name;
    private Ammo cost;
    private ArrayList<SubEffect> effectList;
    private Set<Figure> targetHitSet;
    private boolean used;
    private Weapon myWeapon;
    private int indexTemp;



    public Effect(){};

    public Effect(String name, Ammo cost,ArrayList<SubEffect> effectList){
        this.name="Vuoto";
        if(!name.isEmpty())
            this.name=name;
        this.cost=cost;
        this.effectList=effectList;
        this.targetHitSet= new HashSet<>();

        this.used= false;
        this.indexTemp=0;

    }


        public Ammo getCost() {
        return cost;
    }

    public void setCost(Ammo cost) {
        this.cost = cost;
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

    public boolean executeEffect() {
        SubEffect e;
        targetHitSet=myWeapon.getOwner().allFigures();
        targetHitSet.remove(myWeapon.getOwner());

        for(; indexTemp<effectList.size();indexTemp++) {
            e = effectList.get(indexTemp);
            if(targetHitSet==null){
                indexTemp--;
                return false;
            }
            targetHitSet = e.applyEffect(myWeapon, targetHitSet);
        }
        if(targetHitSet==null){
            indexTemp--;
            return false;
        }
        indexTemp=0;
        used=true;
        return true;
    }

    /**
     *Used by {@link Weapon} class to reset the state of the effect and sub effect in order to ake it available for further use
     *
     *
     * @author Gregorio Barzasi
     */
    public void resetEffect(){
        indexTemp=0;
        for(SubEffect e:effectList)
            e.resetSubEffect();
        used=false;
    }

    public String getName() {
        return name;
    }

    public boolean isUsed() {
        return used;
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }

    public int getIndexTemp() {
        return indexTemp;
    }
}

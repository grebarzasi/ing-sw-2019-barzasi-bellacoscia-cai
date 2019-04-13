package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.Ammo;

import java.util.ArrayList;
/**
 *Used by {@link Weapon} class to represent effects.
 * Works mainly using a combination of {@link SubEffect}
 * @author Gregorio Barzasi
 */
public class Effect {
    private Ammo cost;
    private ArrayList<SubEffect> effectList;
    private ArrayList<Player> targetHitList;
    private boolean used;

    public Effect(Ammo cost,ArrayList<SubEffect> EffectList){
        this.cost=cost;
        this.effectList=EffectList;
        this.targetHitList=null;
        this.used= false;
    }

    public Ammo getCost() {
        return cost;
    }

    public ArrayList<SubEffect> getEffectList() {
        return effectList;
    }

    public void executeEffect(){
    }

}

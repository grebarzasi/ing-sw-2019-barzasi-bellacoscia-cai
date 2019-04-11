package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.Ammo;

import java.util.ArrayList;

public class Effect {
    private Ammo cost;
    private ArrayList<SubEffect> EffectList;
    private ArrayList<Player> TargetHitList;

    public Effect(Ammo cost,ArrayList<SubEffect> EffectList){
        this.cost=cost;
        this.EffectList=EffectList;
        this.TargetHitList=null;
    }

    public void executeEffect(){
    }

}

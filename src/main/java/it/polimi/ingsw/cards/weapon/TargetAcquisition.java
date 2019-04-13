package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.ArrayList;

/**
 *Used by {@link Effect} class to aim the effect to a specific target.
 * Works by "filtering" step by step the available target.
 * @author Gregorio Barzasi
 */

public class TargetAcquisition implements SubEffect {



    private ArrayList<Player> target;
    private ArrayList<AimingFilter> aimRoutine;

    public TargetAcquisition( ArrayList<AimingFilter> aimRoutine){
        this.aimRoutine=aimRoutine;

    }

    public ArrayList<Player> getTarget() {
        return target;
    }

    public ArrayList<AimingFilter> getAimRoutine() {
        return aimRoutine;
    }

    public void applyEffect(){

    }

}
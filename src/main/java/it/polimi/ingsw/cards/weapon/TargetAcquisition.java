package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.ArrayList;

/**
 *Used by {@link Effect} class to aim the effect to a specific target.
 * Works by "filtering" step by step the available target.
 * @author Gregorio Barzasi
 */

public abstract class TargetAcquisition implements SubEffect {



    private ArrayList<Player> target;
    private ArrayList<TargetAcquisition> aimRoutine;

    public abstract void filter();
    public void applyEffect(){

    }

}
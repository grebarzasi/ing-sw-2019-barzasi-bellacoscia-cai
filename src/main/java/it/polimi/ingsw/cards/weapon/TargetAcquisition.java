package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *Used by {@link Effect} class to aim the effect to a specific target.
 * Works by "filtering" step by step the available target.
 * @author Gregorio Barzasi
 */

public class TargetAcquisition implements SubEffect {

    private ArrayList<AimingFilter> aimRoutine;

    public TargetAcquisition( ArrayList<AimingFilter> aimRoutine){
        this.aimRoutine=aimRoutine;

    }

    public ArrayList<AimingFilter> getAimRoutine() {
        return aimRoutine;
    }

    public Set<Player> applyEffect(Weapon w, Set<Player> p){
        Iterator<AimingFilter> aimingIterator = aimRoutine.iterator();
        while (aimingIterator.hasNext())
            p = aimingIterator.next().filter(w,p);
       return p;
    }

}
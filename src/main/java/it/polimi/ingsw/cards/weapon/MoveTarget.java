package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;

import java.util.Set;

/**
 * Effect that moves player around ( also the weapon owner, like and extra move);
 * you can define the final position for the target and also if the move could append in any direction or not;
 * @author Gregorio Barzasi
 */

public class MoveTarget implements SubEffect {

    private int maxSteps;
    private String finalPos;
    private boolean directional;

    public MoveTarget(int maxSteps, String finalPos, boolean directional) {
        this.maxSteps=maxSteps;
        this.finalPos=finalPos;
        this.directional=directional;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public Set<Figure> applyEffect(Weapon w, Set<Figure> p){
            return p;
        //implement
    }

}

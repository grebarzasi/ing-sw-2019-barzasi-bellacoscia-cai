package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.Set;

public class MoveTarget implements SubEffect {

    private int maxSteps;

    public MoveTarget(int maxSteps) {
        this.maxSteps=maxSteps;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public Set<Player> applyEffect(Weapon w, Set<Player> p){
        //implement
        return p;
    }

}

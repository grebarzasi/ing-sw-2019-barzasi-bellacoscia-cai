package it.polimi.ingsw.control;

import it.polimi.ingsw.cards.Effect;

public class MoveTarget implements Effect {

    private int maxSteps;

    public MoveTarget() {
    }

    public abstract void applyEffect();

}

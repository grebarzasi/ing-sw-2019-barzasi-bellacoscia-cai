package it.polimi.ingsw.cards.weapon;

public class MoveTarget implements SubEffect {

    private int maxSteps;

    public MoveTarget(int maxSteps) {
        this.maxSteps=maxSteps;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public void applyEffect(){

    }

}

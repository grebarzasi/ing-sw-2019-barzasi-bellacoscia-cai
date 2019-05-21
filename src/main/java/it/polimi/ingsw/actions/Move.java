package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;

/**
 * @author Gregorio Barzasi
 */

public class Move implements SubAction {

    private int maxSteps;

    public Move(int maxSteps) {
        this.maxSteps=maxSteps;

    }


    public void doAction(Figure target){
        /*Square s;
        do {
            s = target.getModel().askDestination();
        }while(target.distanceTo(s)>maxSteps);
        target.setPosition(s);*/
    }
}

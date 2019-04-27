package it.polimi.ingsw.actions;

import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;

/**
 * @author Gregorio Barzasi
 */

public class Move implements SubAction {

    private int maxSteps;

    public Move(int maxSteps) {
        this.maxSteps=maxSteps;

    }


    public void doAction(Player target){
        Square s;
        do {
            s = target.getControllerServer().askPosition();
        }while(target.distanceTo(s)>maxSteps);
        target.setPosition(s);
    }
}

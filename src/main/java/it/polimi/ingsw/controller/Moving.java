package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * Controller state of moving your figure
 */

public class Moving implements ControllerState{

    private int range;

    private static final int height = 3;
    private static final int width = 4;

    private Controller controller;

    public Moving(Controller controller) {
        this.controller = controller;
    }

    /**
     * Generates a list of squares the current player can move to based on range
     * and send them to the view, takes an input to the view and moves the figure
     * to that square
     */

    @Override
    public void command() {

        ArrayList<Square> options;

        options = this.controller.canGo(this.controller.getCurrentPlayer(),this.range);

        Square choice = this.controller.getView().showPossibleMoves(options, false);

        if(choice == null){
            this.controller.update();
            this.controller.goBack();
        }else{
            this.controller.update();
            this.controller.getCurrentPlayer().setPosition(choice);
            this.controller.dereaseMoveLeft();
            this.controller.goBack();
            this.controller.currentState.command();
        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

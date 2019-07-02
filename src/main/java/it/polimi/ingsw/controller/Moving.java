package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * controller state of moving your figure
 */

public class Moving implements ControllerState{

    /**
     * the amount of possible movements
     */
    private int range;

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    Moving(Controller controller) {
        this.controller = controller;
    }

    /**
     * Generates a list of squares the current player can move to based on range
     * and send them to the view, takes an input to the view and moves the figure
     * to that square
     */

    @Override
    public void executeState() {

        ArrayList<Square> options;

        options = this.controller.canGo(this.controller.getCurrentPlayer(),this.range);

        this.controller.checkInactivity();
        Square choice = this.controller.getView().showPossibleMoves(options, false);

        if (choice == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choice == null){

            this.controller.update();
            this.controller.goBack();

        }else{

            this.controller.getCurrentPlayer().setPosition(choice);
            this.controller.decreaseMoveLeft();
            this.controller.update();
            this.controller.goBack();

        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

public class Moving implements ControllerState{

    private int range;

    private static final int height = 3;
    private static final int width = 4;

    private Controller controller;

    public Moving(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

        ArrayList<Square> options;

        options = this.controller.canGo(this.controller.getCurrentPlayer(),this.range);

        Square choice = this.controller.getView().showPossibleMoves(options);

        if(choice == null){
            this.controller.update();
            this.controller.goBack();
            this.controller.currentState.command();
        }else{
            this.controller.update();
            this.controller.getCurrentPlayer().setPosition(choice);
            this.controller.getModel().setMovesLeft(this.controller.getMovesLeft() - 1);
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

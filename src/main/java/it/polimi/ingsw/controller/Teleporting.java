package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

public class Teleporting implements ControllerState {

    private PowerUp using;

    private static final int height = 3;
    private static final int width = 4;


    private Controller controller;

    public Teleporting(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

        ArrayList<Square> options = new ArrayList<>();

        int row;
        int column;

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {
                options.add(this.controller.getModel().getBoard().getMap().getSquareMatrix()[row][column]);
            }
        }

        options.remove(this.controller.getCurrentPlayer().getPosition());
        Square choice = this.controller.getView().showPossibleMoves(options);

        if(choice == null){
            this.controller.goBack();
        }else{

            this.controller.getCurrentPlayer().setPosition(choice);
            this.controller.setCurrentState(this.controller.choosingMove);
            this.controller.getCurrentPlayer().removePowerUp(this.using);
            this.controller.getModel().setMovesLeft(this.controller.getModel().getMovesLeft() - 1);
        }

    }


    public PowerUp getUsing() {
        return using;
    }

    public void setUsing(PowerUp using) {
        this.using = using;
    }


}

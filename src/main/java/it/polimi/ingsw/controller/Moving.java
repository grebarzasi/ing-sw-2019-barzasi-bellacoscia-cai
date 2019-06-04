package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

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

        ArrayList<Square> options = new ArrayList<>();

        int row;
        int column;

        for(row = 0; row < height; row++){
            for(column = 0; column < width; column++){

                if(this.controller.getCurrentPlayer().distanceTo(this.controller.getBoard().getMap().getSquareMatrix()[row][column])
                        < this.range){

                    options.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        Square choice = this.controller.getView().showPossibleMoves(options);

        if(choice == null){
            this.controller.goBack();
            this.controller.currentState.command();
        }else{
            this.controller.getCurrentPlayer().setPosition(choice);
            this.controller.getModel().setMovesLeft(this.controller.getMovesLeft() - 1);
            this.controller.goBack();
            this.controller.currentState.command();
        }
    }

}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

/**
 * State in which a player uses a teleporter
 */

public class Teleporting implements ControllerState {

    private PowerUp using;





    private Controller controller;

    Teleporting(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes the player choose a square to teleport to
     */

    @Override
    public void executeState(){

        ArrayList<Square> options = new ArrayList<>();

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {
                options.add(this.controller.getModel().getBoard().getMap().getSquareMatrix()[row][column]);
            }
        }

        options.remove(this.controller.getCurrentPlayer().getPosition());
        Square choice = this.controller.getView().showPossibleMoves(options, false);

        if (choice == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choice == null){
            this.controller.goBack();
        }else{

            this.controller.getCurrentPlayer().setPosition(choice);

            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(this.using);
            this.controller.getCurrentPlayer().removePowerUp(this.using);
            this.controller.update();
            this.controller.goBack();
        }

    }

    void setUsing(PowerUp using) {
        this.using = using;
    }


}

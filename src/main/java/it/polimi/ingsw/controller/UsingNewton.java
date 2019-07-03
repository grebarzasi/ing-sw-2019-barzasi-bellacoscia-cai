package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.map.Map;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.power_up.PowerUp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * State of using a newton powerup
 */

public class UsingNewton implements ControllerState {

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * The PowerUp being currently used
     */
    private PowerUp using;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    UsingNewton(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void executeState(){

        ArrayList<Figure> targets = new ArrayList<>();

        for (Player p : this.controller.getModel().getPlayerList()) {
            if (p != this.controller.getCurrentPlayer()) {
                targets.add(p);
            }
        }

        if(this.controller.hasBot()) {
            targets.add(this.controller.getModel().getBot());
        }

        Set<Figure> targetTemp=new HashSet<>(targets);

        ArrayList<Figure> choiceTemp = this.controller.getView().showTargetAdvanced(targetTemp,1,false,ControllerMessages.ASK_NEWTON_TARGET);

        if (choiceTemp == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choiceTemp == null){
            this.controller.goBack();
        }else {
            Figure choice = choiceTemp.get(0);

            int row;
            int column;
            ArrayList<Square> options = new ArrayList<>();

            for (row = 0; row < Map.HEIGHT; row++) {
                for (column = 0; column < Map.WIDTH; column++) {

                    if (this.controller.getBoard().getMap().getSquareMatrix()[row][column].isAdjacent(choice.getPosition())) {
                        options.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
                    }
                }
            }

            Square target = this.controller.getView().showPossibleMoves(options, false);

            if (target == null&&(controller.getView().isDisconnected()||controller.getView().isInactive()))
                controller.endTurn();

            choice.setPosition(target);
            this.controller.getCurrentPlayer().removePowerUp(this.using);
            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(this.using);
            this.controller.update();
            this.controller.goBack();
        }
    }

    void setUsing(PowerUp using) {
        this.using = using;
    }
}

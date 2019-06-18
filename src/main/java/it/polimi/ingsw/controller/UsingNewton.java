package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * State of using a newton powerup
 */

public class UsingNewton implements ControllerState {

    private Controller controller;
    private PowerUp using;

    private static final int WIDTH = 4;
    private static final int HEIGHT = 3;

    UsingNewton(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command(){

        ArrayList<Figure> targets = new ArrayList<>();

        for (Player p : this.controller.getModel().getPlayerList()) {
            if (p != this.controller.getCurrentPlayer()) {
                targets.add(p);
            }
        }
        Set<Figure> targetTemp=new HashSet<>(targets);

        ArrayList<Figure> choiceTemp = this.controller.getView().showTargetAdvanced(targetTemp,1,false,"Seleziona un bersaglio da spostare:");

        if (choiceTemp == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choiceTemp == null){
            this.controller.goBack();
        }else {
            Figure choice = choiceTemp.get(0);

            int row;
            int column;
            ArrayList<Square> options = new ArrayList<>();

            for (row = 0; row < HEIGHT; row++) {
                for (column = 0; column < WIDTH; column++) {

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

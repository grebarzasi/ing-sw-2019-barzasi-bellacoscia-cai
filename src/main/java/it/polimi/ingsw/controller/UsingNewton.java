package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class UsingNewton implements ControllerState {

    private Controller controller;

    private static final int width = 4;
    private static final int height = 3;

    public UsingNewton(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void move() {

    }

    @Override
    public void pick() {

    }

    @Override
    public void choose() {

        ArrayList<Figure> targets = new ArrayList<>();

        for (Player p : this.controller.getModel().getPlayerList()) {
            if (p != this.controller.getCurrentPlayer()) {
                targets.add(p);
            }
        }

        Figure choice = this.controller.getView().singleTargetingShowTarget(targets);

        int row;
        int column;
        ArrayList<Square> options = new ArrayList<>();

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {
                //TODO condition is currently bullshit, needs to be fixed in action builder;
                if (this.controller.getBoard().getMap().getSquareMatrix()[row][column].isAdjacent(choice.getPosition())) {
                    options.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
                }
            }
        }

        Square target = this.controller.getView().showPossibleMoves(options);
        choice.setPosition(target);

        this.controller.setCurrentState(this.controller.choosingMove);
    }


}

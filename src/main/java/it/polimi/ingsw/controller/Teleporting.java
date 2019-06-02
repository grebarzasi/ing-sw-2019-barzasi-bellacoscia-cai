package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Teleporting implements ControllerState {

    private static final int height = 3;
    private static final int width = 4;


    private Controller controller;

    public Teleporting(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void chooseWeapon() {

    }

    @Override
    public void move() {

    }

    @Override
    public void pick() {

    }

    @Override
    public void choose() {

        ArrayList<Square> options = new ArrayList<>();

        int row;
        int column;

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {
                options.add(this.controller.getModel().getCurrentBoard().getMap().getSquareMatrix()[row][column]);
            }
        }

        options.remove(this.controller.getCurrentPlayer().getPosition());
        Square choice = this.controller.getView().showPossibleMoves(options);

        this.controller.getCurrentPlayer().setPosition(choice);
        this.controller.setCurrentState(this.controller.choosingMove);


    }

    @Override
    public void chooseMove() {

    }

    @Override
    public void discardPU() {

    }

    @Override
    public void usePU() {

    }

    @Override
    public void teleport(Square teleportHere) {

    }

    @Override
    public void tagback() {

    }

    @Override
    public void Scope(PowerUp choice) {

    }

    @Override
    public void Newton(PowerUp choice) {

    }

    @Override
    public void useNewton(Figure Target, Square moveTo) {

    }
}

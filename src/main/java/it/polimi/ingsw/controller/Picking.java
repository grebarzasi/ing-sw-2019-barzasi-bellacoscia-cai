package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Picking implements ControllerState{

    private Controller controller;

    private static final int width = 4;
    private static final int height = 3;
    private static final int max = 3;

    public Picking(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void command() {

        ArrayList<Square> options = new ArrayList<>();

        int row;
        int column;

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {
                //TODO condition is currently bullshit, needs to be fixed in action builder;
                if (this.controller.getCurrentPlayer().distanceTo(this.controller.getBoard().getMap().getSquareMatrix()[row][column]) < 3) {
                    options.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
                }
            }
        }

        Square choice = this.controller.getView().showPossibleMoves(options);

        if (choice.isSpawn()) {
            this.controller.setCurrentState(this.controller.pickingWeapon);
        } else {
            this.controller.getCurrentPlayer().pickAmmo();
            if (this.controller.getCurrentPlayer().getPowerupList().size() == max) {
                this.choosePU();
            }
        }


    }

    private void choosePU() {

        PowerUp chosen = this.controller.getView().showPowerUp(this.controller.getCurrentPlayer().getPowerupList());

        if (this.controller.getCurrentPlayer().getPowerupList().size() < 3) {

            this.controller.getCurrentPlayer().addPowerUp(chosen);

        } else {

            ArrayList<PowerUp> options;
            options = this.controller.getCurrentPlayer().getPowerupList();
            options.add(chosen);
            PowerUp discarded = this.controller.getView().showPowerUp(options);
            options.remove(discarded);
            this.controller.getCurrentPlayer().getPowerupList().clear();
            this.controller.getCurrentPlayer().getPowerupList().addAll(options);

        }

    }

}

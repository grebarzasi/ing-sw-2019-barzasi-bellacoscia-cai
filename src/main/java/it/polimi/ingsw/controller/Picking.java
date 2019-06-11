package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Picking implements ControllerState{

    private int range;
    private Controller controller;

    private static final int max = 3;

    public Picking(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void command() {

        ArrayList<Square> options;

        options = this.controller.canGo(this.controller.getCurrentPlayer(),this.range);

        Square choice = this.controller.getView().showPossibleMoves(options);

        if (choice.isSpawn()) {
            this.controller.setCurrentState(this.controller.pickingWeapon);
            this.controller.getCurrentState().command();
        } else {
            this.controller.getCurrentPlayer().pickAmmo();
            if (this.controller.getCurrentPlayer().getPowerupList().size() == max) {
                this.choosePU();
            }
        }


    }

    private void choosePU() {

        PowerUp chosen = this.controller.getView().showPowerUp(this.controller.getCurrentPlayer().getPowerupList());

        if (this.controller.getCurrentPlayer().getPowerupList().size() < max) {

            this.controller.getCurrentPlayer().addPowerUp(chosen);
            this.controller.update();
            this.controller.goBack();

        } else {

            ArrayList<PowerUp> options;
            options = this.controller.getCurrentPlayer().getPowerupList();
            options.add(chosen);
            PowerUp discarded = this.controller.getView().showPowerUp(options);
            options.remove(discarded);
            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(discarded);
            this.controller.getCurrentPlayer().getPowerupList().clear();
            this.controller.getCurrentPlayer().getPowerupList().addAll(options);
            this.controller.dereaseMoveLeft();
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

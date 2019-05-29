package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Picking implements ControllerState{

    private Controller controller;

    public Picking(Controller controller) {
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

    @Override
    public void chooseMove() {

    }

    @Override
    public void discardPU() {

    }

    @Override
    public void discardWeapon() {

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

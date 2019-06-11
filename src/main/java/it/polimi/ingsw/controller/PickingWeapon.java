package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.SpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PickingWeapon implements ControllerState {

    private static final int max = 3;
    private Controller controller;

    public PickingWeapon(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

        ArrayList<Weapon> options;

        options = ((SpawnSquare) this.controller.getCurrentPlayer().getPosition()).getArmory().getWeaponList();
        Weapon choice = this.controller.getView().showWeapon(options);

        ((SpawnSquare) this.controller.getCurrentPlayer().getPosition()).getArmory().getWeaponList().remove(choice);

        if (this.controller.getCurrentPlayer().getWeaponsList().size() < max) {
            this.controller.getCurrentPlayer().getWeaponsList().add(choice);
            this.controller.dereaseMoveLeft();
        } else {
            this.discardWeapon(choice);
        }

    }


    private void discardWeapon(Weapon arg) {

        ArrayList<Weapon> options = this.controller.getCurrentPlayer().getWeaponsList();

        Weapon choice = this.controller.getView().showWeapon(options);
        options.remove(choice);
        options.add(arg);

        this.controller.getCurrentPlayer().getWeaponsList().clear();
        this.controller.getCurrentPlayer().getWeaponsList().addAll(options);

        ((SpawnSquare) this.controller.getCurrentPlayer().getPosition()).getArmory().getWeaponList().add(choice);
        this.controller.dereaseMoveLeft();
        this.controller.goBack();

    }
}

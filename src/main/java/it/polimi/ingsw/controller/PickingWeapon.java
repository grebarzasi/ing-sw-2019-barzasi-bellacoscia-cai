package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.SpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PickingWeapon implements ControllerState {

    private Controller controller;

    public PickingWeapon(Controller controller) {
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

        ArrayList<Weapon> options = new ArrayList<>();

        options = ((SpawnSquare) this.controller.getCurrentPlayer().getPosition()).getArmory().getWeaponList();
        Weapon choice = this.controller.getView().showWeapon(options);

        ((SpawnSquare) this.controller.getCurrentPlayer().getPosition()).getArmory().getWeaponList().remove(choice);

        if (this.controller.getCurrentPlayer().getWeaponsList().size() < 3) {
            this.controller.getCurrentPlayer().getWeaponsList().add(choice);
        } else {
            this.discardWeapon(choice);
        }

    }

    @Override
    public void chooseMove() {

    }

    @Override
    public void discardPU() {

    }

    private void discardWeapon(Weapon arg) {

        ArrayList<Weapon> options = this.controller.getCurrentPlayer().getWeaponsList();
        options.add(arg);

        Weapon choice = this.controller.getView().showWeapon(options);
        options.remove(choice);

        this.controller.getCurrentPlayer().setWeaponsList(options);

        ((SpawnSquare) this.controller.getCurrentPlayer().getPosition()).getArmory().getWeaponList().add(choice);
        this.controller.setCurrentState(this.controller.choosingMove);
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

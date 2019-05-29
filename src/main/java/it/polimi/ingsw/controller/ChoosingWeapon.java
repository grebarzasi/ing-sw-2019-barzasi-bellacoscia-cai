package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class ChoosingWeapon implements ControllerState {

    private Controller controller;

    public ChoosingWeapon(Controller controller) {
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

    /**
     * Makes the player choose a weapon through the view and afterwards
     * sets the current state of controller to shooting
     */

    @Override
    public void choose() {

        //load the current player's weapons into "options"
        ArrayList<Weapon> options = this.controller.getCurrentPlayer().getWeaponsList();

        //Displays the options to the view and the view returns the chosen weapon
        Weapon choice = this.controller.getView().showWeapon(options);

        //Sets the shooting state's weapon to the chosen one and changes the current state to shooting
        ((Shooting) this.controller.getShooting()).setShootingWith(choice);
        this.controller.setCurrentState(this.controller.shooting);

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

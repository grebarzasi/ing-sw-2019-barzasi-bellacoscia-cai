package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * State in which a player can move, reload and shoot
 */

public class FrenzySpecialAction implements ControllerState {


    private int range;
    private Controller controller;

    FrenzySpecialAction(Controller controller) {
        this.controller = controller;
    }

    /**
     * Takes a square input from player and moves the player to that square;
     * Asks the player to reload, if yes asks the weapon to reload and reloads it
     * If a player can shoot set the player state to shooting
     */


    @Override
    public void command() {

        ArrayList<Square> options = this.controller.canGo(this.controller.getCurrentPlayer(), this.range);

        Square choice = this.controller.getView().showPossibleMoves(options, false);
        this.controller.getCurrentPlayer().setPosition(choice);

        if(choice == null){
            this.controller.goBack();
        }else {
            while (this.controller.getView().showBoolean("Vuoi sfruttare la ricarica?")) {

                ArrayList<Weapon> reloadOptions = this.controller.getCurrentPlayer().getWeaponsList();
                Weapon reloadChoice = this.controller.getView().showWeapon(reloadOptions);
                boolean check = reloadChoice.reload();

                if (!check) {
                    this.controller.getView().displayMessage("Non possiedi abbastanza risorse per caricare l'arma");
                }

            }
        }

        ArrayList<Weapon> weaponChoices = this.controller.getCurrentPlayer().getWeaponsList();
        weaponChoices.removeIf(weapon -> !weapon.isLoaded());

        Weapon shootingWith = this.controller.getView().showWeapon(weaponChoices);

        if(shootingWith == null){
            this.controller.decreaseMoveLeft();
            this.controller.update();
            this.controller.goBack();
        }else {
            this.controller.setCurrentState(this.controller.shooting);
            ((Shooting) this.controller.getCurrentState()).setShootingWith(shootingWith);

            this.controller.getCurrentState().command();
        }

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

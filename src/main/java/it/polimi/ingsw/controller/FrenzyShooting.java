package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * State in which a player can move, reload and shoot
 */

public class FrenzyShooting implements ControllerState {


    /**
     * the amount of movement the player can perform before reloading and then shoot
     */
    private int range;

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    FrenzyShooting(Controller controller) {
        this.controller = controller;
    }

    /**
     * Takes a square input from player and moves the player to that square;
     * Asks the player to reload, if yes asks the weapon to reload and reloads it
     * If a player can shoot set the player state to shooting
     */


    @Override
    public void executeState() {

        ArrayList<Square> options = this.controller.canGo(this.controller.getCurrentPlayer(), this.range);

        this.controller.checkInactivity();
        Square choice = this.controller.getView().showPossibleMoves(options, false);
        this.controller.getCurrentPlayer().setPosition(choice);

        this.controller.decreaseMoveLeft();
        this.controller.update();

        if(choice == null){
            this.controller.goBack();
        }else {
            if (this.controller.getView().showBoolean(ControllerMessages.ASK_FRENZY_RELOAD)) {

                ArrayList<Weapon> reloadOptions = this.controller.getCurrentPlayer().getWeaponsList();
                Weapon reloadChoice = this.controller.getView().showWeapon(reloadOptions);
                if(reloadChoice != null) {

                    boolean check = reloadChoice.reload();

                    if (!check) {
                        this.controller.getView().displayMessage(ControllerMessages.CANNOT_RELOAD);
                    }
                }
            }
        }

        ArrayList<Weapon> weaponChoices = this.controller.getCurrentPlayer().getWeaponsList();
        weaponChoices.removeIf(weapon -> !weapon.isLoaded());

        Weapon shootingWith = this.controller.getView().showWeapon(weaponChoices);

        if(shootingWith == null){
            this.controller.update();
            this.controller.goBack();
        }else {
            this.controller.setCurrentState(this.controller.shooting);
            ((Shooting) this.controller.getCurrentState()).setShootingWith(shootingWith);

            this.controller.getCurrentState().executeState();
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

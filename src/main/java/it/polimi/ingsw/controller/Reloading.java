package it.polimi.ingsw.controller;

import it.polimi.ingsw.cards.weapon.Weapon;
import java.util.ArrayList;

/**
 * State of reloading a weapon
 */

public class Reloading implements ControllerState {

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    Reloading(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes a player choose one of his weapons,
     * if he can afford it, reloads the weapon
     */

    @Override
    public void executeState(){

        ArrayList<Weapon> options = new ArrayList<>(this.controller.getCurrentPlayer().getWeaponsList());

        options.removeIf(Weapon::isLoaded);

        this.controller.checkInactivity();
        Weapon choice = this.controller.getView().showWeapon(options);

        //noinspection ConstantConditions
        if(choice == null){
            this.controller.goBack();
        }else {

            boolean check = choice.reload();
            if (!check) {
                this.controller.getView().displayMessage(ControllerMessages.CANNOT_RELOAD);
            }

            this.controller.update();
            this.controller.goBack();
        }

    }


}

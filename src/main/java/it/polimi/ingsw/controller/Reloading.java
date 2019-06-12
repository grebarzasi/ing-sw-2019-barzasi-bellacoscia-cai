package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * State of reloading a weapon
 */

public class Reloading implements ControllerState {

    private Controller controller;

    public Reloading(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes a player choose one of his weapons,
     * if he can afford it, reloads the weapon
     */

    @Override
    public void command() {

        ArrayList<Weapon> options = this.controller.getCurrentPlayer().getWeaponsList();
        Weapon choice = this.controller.getView().showWeapon(options);

        boolean check = choice.reload();

        if(choice == null){
            this.controller.goBack();
        }else {

            if (check == false) {
                this.controller.getView().displayMessage("Non possiedi le risorse per caricare l'arma selezionata");
            }

            this.controller.update();
            this.controller.goBack();
        }

    }


}

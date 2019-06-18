package it.polimi.ingsw.controller;

import it.polimi.ingsw.cards.weapon.Weapon;
import java.util.ArrayList;

/**
 * State of reloading a weapon
 */

public class Reloading implements ControllerState {

    private Controller controller;

    Reloading(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes a player choose one of his weapons,
     * if he can afford it, reloads the weapon
     */

    @Override
    public void command(){

        ArrayList<Weapon> options = new ArrayList<>(this.controller.getCurrentPlayer().getWeaponsList());

        for(Weapon w: options){
            if(w.isLoaded()){
                options.remove(w);
            }
        }

        Weapon choice = this.controller.getView().showWeapon(options);

        boolean check = choice.reload();

        //noinspection ConstantConditions
        if(choice == null){
            this.controller.goBack();
        }else {

            if (!check) {
                this.controller.getView().displayMessage("Non possiedi le risorse per caricare l'arma selezionata");
            }

            this.controller.update();
            this.controller.goBack();
        }

    }


}

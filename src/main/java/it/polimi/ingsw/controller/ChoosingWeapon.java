package it.polimi.ingsw.controller;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * Controller state of choosing a weapon
 */

public class ChoosingWeapon implements ControllerState {

    private Controller controller;

    ChoosingWeapon(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes the player choose a weapon through the view and afterwards
     * sets the current state of controller to shooting
     */

    @Override
    public void command() {

        //load the current player's weapons into "options"

        ArrayList<Weapon> options = new ArrayList<>(this.controller.getCurrentPlayer().getWeaponsList());

        for(Weapon w : options){
            if(!w.isLoaded()){
                options.remove(w);
            }
        }

        //Displays the options to the view and the view returns the chosen weapon
        Weapon choice = this.controller.getView().showWeapon(options);

        if (choice == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choice == null){
            this.controller.goBack();
            this.controller.choosingMove.command();
        }else {
            //Sets the shooting state's weapon to the chosen one and changes the current state to shooting
            ((Shooting) this.controller.getShooting()).setShootingWith(choice);
            this.controller.setCurrentState(this.controller.shooting);
            this.controller.currentState.command();
        }
    }


}

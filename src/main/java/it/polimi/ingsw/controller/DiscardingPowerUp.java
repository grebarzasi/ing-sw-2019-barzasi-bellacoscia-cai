package it.polimi.ingsw.controller;

import it.polimi.ingsw.cards.power_up.PowerUp;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller state of discarding a powerup to convert to ammunition
 */

public class DiscardingPowerUp implements ControllerState{

    private Controller controller;

    DiscardingPowerUp(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes the player choose a powerup to discard
     * and adds the corresponding ammunition
     */

    @Override
    public void command() {

        ArrayList<PowerUp> options = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());
        PowerUp choice = this.controller.getView().showPowerUp(options);

        if (choice == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choice == null){
            this.controller.goBack();
        }else {
            this.controller.getCurrentPlayer().getPersonalBoard().addAmmo(choice.getAmmoOnDiscard());
            this.controller.getCurrentPlayer().getPowerupList().remove(choice);
            this.controller.getBoard().getPowerupDeck().getDiscarded().add(choice);

            this.controller.goBack();
        }

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

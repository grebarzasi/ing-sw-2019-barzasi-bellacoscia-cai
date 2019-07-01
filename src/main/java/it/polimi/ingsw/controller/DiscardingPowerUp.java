package it.polimi.ingsw.controller;

import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

/**
 * Controller state of discarding a powerup to convert to ammunition
 */

public class DiscardingPowerUp implements ControllerState{

    /**
     * controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    DiscardingPowerUp(Controller controller) {
        this.controller = controller;
    }

    /**
     * Makes the player choose a powerup to discard
     * and adds the corresponding ammunition
     */

    @Override
    public void executeState() {

        ArrayList<PowerUp> options = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());
        this.controller.checkInactivity();
        PowerUp choice = this.controller.getView().showPowerUp(options);

        if (choice == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(choice == null){
            this.controller.goBack();
        }else {
            this.controller.getCurrentPlayer().getPersonalBoard().addAmmo(choice.getAmmoOnDiscard());
            this.controller.getCurrentPlayer().getPowerupList().remove(choice);
            this.controller.getBoard().getPowerupDeck().getDiscarded().add(choice);

            this.controller.update();
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

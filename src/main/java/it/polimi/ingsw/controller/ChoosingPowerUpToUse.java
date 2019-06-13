package it.polimi.ingsw.controller;

import it.polimi.ingsw.cards.power_up.PowerUp;

/**
 * Controller state of choosing a PowerUp to use
 */

public class ChoosingPowerUpToUse implements ControllerState {

    private Controller controller;

    public ChoosingPowerUpToUse(Controller controller) {
        this.controller = controller;
    }

    /**
     * takes the choice of PowerUp and sets the controller state to the corresponding choice
     */

    @Override
    public void command() {

        PowerUp chosen = this.controller.getView().showPowerUp(this.controller.getCurrentPlayer().getPowerupList());

        if(chosen == null){

            this.controller.goBack();

        }else {

            switch (chosen.getName()) {
                case "Raggio Cinetico":
                    this.controller.setCurrentState(this.controller.usingNewton);
                    ((UsingNewton)this.controller.usingNewton).setUsing(chosen);
                    this.controller.currentState.command();
                    break;

                case "Teletrasporto":
                    this.controller.setCurrentState(this.controller.teleporting);
                    ((Teleporting)this.controller.teleporting).setUsing(chosen);
                    this.controller.currentState.command();
                    break;
            }

        }


    }

}

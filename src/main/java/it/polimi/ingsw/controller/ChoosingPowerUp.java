package it.polimi.ingsw.controller;

import it.polimi.ingsw.cards.power_up.PowerUp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller state of choosing a PowerUp to use
 */

public class ChoosingPowerUp implements ControllerState {

    private Controller controller;

    public ChoosingPowerUp(Controller controller) {
        this.controller = controller;
    }

    /**
     * takes the choice of PowerUp and sets the controller state to the corresponding choice
     */

    @Override
    public void command() throws IOException {

        ArrayList<PowerUp> options = new ArrayList<>();
        options.addAll(this.controller.getCurrentPlayer().getPowerupList());
//
//        Controller.filterPUs(options,PowerUp.TELEPORTER );
//        Controller.filterPUs(options,PowerUp.NEWTON);

        PowerUp chosen = this.controller.getView().showPowerUp(options);
        if(chosen == null){

            this.controller.goBack();

        }else {

            switch (chosen.getName()) {
                case PowerUp.NEWTON:
                    this.controller.setCurrentState(this.controller.usingNewton);
                    ((UsingNewton)this.controller.usingNewton).setUsing(chosen);
                    this.controller.currentState.command();
                    break;

                case PowerUp.TELEPORTER :
                    this.controller.setCurrentState(this.controller.teleporting);
                    ((Teleporting)this.controller.teleporting).setUsing(chosen);
                    this.controller.currentState.command();
                    break;
            }

        }


    }

}

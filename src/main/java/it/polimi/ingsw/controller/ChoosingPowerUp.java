package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.cards.power_up.PowerUp;

import java.util.ArrayList;


/**
 * controller state of choosing a PowerUp to use
 */

public class ChoosingPowerUp implements ControllerState {

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    ChoosingPowerUp(Controller controller) {
        this.controller = controller;
    }

    /**
     * takes the choice of PowerUp and sets the controller state to the corresponding choice
     */

    @Override
    public void executeState() {


        ArrayList<PowerUp> options = new ArrayList<>();


        //Filters the powerup list to only those that can be used
        ArrayList<PowerUp> newtons = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());
        ArrayList<PowerUp> teleporters = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());

        Controller.filterPUs(teleporters,PowerUp.TELEPORTER );
        Controller.filterPUs(newtons,PowerUp.NEWTON);

        options.addAll(newtons);
        options.addAll(teleporters);

        this.controller.checkInactivity();
        //takes the player choice
        PowerUp chosen = this.controller.getView().showPowerUp(options);

        if (chosen == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(chosen == null){

            this.controller.goBack();

        }else {

            //sets the next state of the game accordingly
            switch (chosen.getName()) {
                case PowerUp.NEWTON:
                    this.controller.setCurrentState(this.controller.usingNewton);
                    ((UsingNewton)this.controller.usingNewton).setUsing(chosen);
                    this.controller.currentState.executeState();
                    break;

                case PowerUp.TELEPORTER :
                    this.controller.setCurrentState(this.controller.teleporting);
                    ((Teleporting)this.controller.teleporting).setUsing(chosen);
                    this.controller.currentState.executeState();
                    break;
            }

        }


    }

}

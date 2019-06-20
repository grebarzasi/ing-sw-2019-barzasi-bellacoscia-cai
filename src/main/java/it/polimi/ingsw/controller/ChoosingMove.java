package it.polimi.ingsw.controller;


import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.actions.ActionBuilder;

import java.util.ArrayList;


public class ChoosingMove implements ControllerState{

    private Controller controller;

    ChoosingMove(Controller controller) {
        this.controller = controller;
    }

    /**
     * Sets the controller state to the corresponding action the player chooses
     */

    @Override
    public void executeState(){



        if(this.controller.getCurrentPlayer().isDead()){

            //Server log
            System.out.println(this.controller.getCurrentPlayer().getUsername() + " playing as " +this.controller.getCurrentPlayer().getCharacter() + " is Spawning. \n");
            this.controller.setCurrentState(this.controller.spawning);
            this.controller.getCurrentState().executeState();

        }else {

            ArrayList<Action> options = ActionBuilder.build(controller.getCurrentPlayer(), this.controller.getModel().isFrenzy());

            this.controller.checkInactivity();
            Action choice = this.controller.getView().showActions(options);

            if (choice == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
                controller.endTurn();
            } else if (choice == null) {
                this.executeState();
            } else {

                switch (choice.getDescription()) {


                    //sets the state to moving and sets the range accordingly
                    case "Move":
                        this.controller.setCurrentState(this.controller.moving);
                        ((Moving) this.controller.moving).setRange(choice.getRange());
                        this.controller.currentState.executeState();
                        break;

                    //sets the state to picking and sets the range accordingly
                    case "Pick":
                        this.controller.setCurrentState(this.controller.picking);
                        ((Picking) this.controller.picking).setRange(choice.getRange());
                        this.controller.currentState.executeState();
                        break;

                    //sets the range to shooting and sets the range accordingly
                    case "Shoot":
                        this.controller.setCurrentState(this.controller.choosingWeapon);
                        ((Shooting) this.controller.shooting).setRange(choice.getRange());
                        this.controller.currentState.executeState();
                        break;

                    //sets the range to choosing power up
                    case "PowerUp":
                        this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
                        this.controller.currentState.executeState();
                        break;

                    //sets the state to reloading
                    case "Reload":
                        this.controller.setCurrentState(this.controller.reloading);
                        this.controller.currentState.executeState();
                        break;

                    //sets the state to special weir useless and complicated frenzy action
                    case "Move, Reload and Shoot":
                        this.controller.setCurrentState(this.controller.frenzySpecialAction);
                        this.controller.currentState.executeState();
                        break;

                    //sets the state to discarding a Power Up
                    case "Discard PowerUp":
                        this.controller.setCurrentState(this.controller.discardingPowerUp);
                        this.controller.currentState.executeState();
                        break;

                    //sets the state to using the bot
                    case "Use Bot":
                        this.controller.setCurrentState(this.controller.asBot);
                        this.controller.currentState.executeState();
                        break;

                    //ends the turn
                    case "End Turn":
                        this.controller.endTurn();
                        break;

                }
            }
        }
    }



}

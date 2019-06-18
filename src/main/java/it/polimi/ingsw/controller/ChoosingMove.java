package it.polimi.ingsw.controller;


import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.actions.ActionBuilder;

import java.io.IOException;
import java.util.ArrayList;


public class ChoosingMove implements ControllerState{

    private Controller controller;
    private ArrayList<Action> options;

    public ChoosingMove(Controller controller) {
        this.controller = controller;
    }

    /**
     * Sets the controller state to the corresponding action the player chooses
     */

    @Override
    public void command(){

        if(this.controller.getCurrentPlayer().isDead()){

            //Server log
            System.out.println(this.controller.getCurrentPlayer().getUsername() + " playing as " +this.controller.getCurrentPlayer().getCharacter() + " is Spawning. \n");
            this.controller.setCurrentState(this.controller.spawning);
            this.controller.getCurrentState().command();

        }else {

            this.options = new ArrayList<>();

            options = ActionBuilder.build(controller.getCurrentPlayer(), this.controller.getModel().isFrenzy());

            Action choice = this.controller.getView().showActions(options);

            if (choice == null) {
                this.command();
            } else {

                switch (choice.getDescription()) {


                    //sets the state to moving and sets the range accordingly
                    case "Move":
                        this.controller.setCurrentState(this.controller.moving);
                        ((Moving) this.controller.moving).setRange(choice.getRange());
                        this.controller.currentState.command();
                        break;

                    //sets the state to picking and sets the range accordingly
                    case "Pick":
                        this.controller.setCurrentState(this.controller.picking);
                        ((Picking) this.controller.picking).setRange(choice.getRange());
                        this.controller.currentState.command();
                        break;

                    //sets the range to shooting and sets the range accordingly
                    case "Shoot":
                        this.controller.setCurrentState(this.controller.choosingWeapon);
                        ((Shooting) this.controller.shooting).setRange(choice.getRange());
                        this.controller.currentState.command();
                        break;

                    //sets the range to choosing power up
                    case "PowerUp":
                        this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
                        this.controller.currentState.command();
                        break;

                    //sets the state to reloading
                    case "Reload":
                        this.controller.setCurrentState(this.controller.reloading);
                        this.controller.currentState.command();
                        break;

                    //sets the state to special weir useless and complicated frenzy action
                    case "Move, Reload and Shoot":
                        this.controller.setCurrentState(this.controller.frenzySpecialAction);
                        this.controller.currentState.command();
                        break;

                    case "Discard PowerUp":
                        this.controller.setCurrentState(this.controller.discardingPowerUp);
                        this.controller.currentState.command();
                        break;

                    case "Use Bot":
                        this.controller.setCurrentState(this.controller.asBot);
                        this.controller.currentState.command();
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

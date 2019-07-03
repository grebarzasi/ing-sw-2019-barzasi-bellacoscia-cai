package it.polimi.ingsw.controller;


import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.controller.actions.Action;
import it.polimi.ingsw.controller.actions.ActionBuilder;

import java.util.ArrayList;


public class ChoosingMove implements ControllerState{

    /**
     * the controller reference
     */

    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    ChoosingMove(Controller controller) {
        this.controller = controller;
    }

    /**
     * Sets the controller state to the corresponding action the player chooses
     */

    @Override
    public void executeState(){

        for(Player p: this.controller.getModel().getPlayerList()) {

            if (p.isDead() && this.controller.getModel().getTurn()>=this.controller.getModel().getPlayerList().size()) {

                ((Spawning)this.controller.spawning).setReviving(p);
                this.controller.setCurrentState(this.controller.spawning);
                this.controller.currentState.executeState();
            }
        }

        this.controller.update();

        if(this.controller.getCurrentPlayer().isDead()){
            ((Spawning)this.controller.spawning).setReviving(this.controller.getCurrentPlayer());
            this.controller.setCurrentState(this.controller.spawning);
            this.controller.currentState.executeState();
        }

        ArrayList<Action> options = ActionBuilder.build(controller.getCurrentPlayer(), this.controller.getModel().isFrenzy());

        this.controller.checkInactivity();
        Action choice = this.controller.getView().showActions(options);

        if (controller.getView().isDisconnected() || controller.getView().isInactive()) {
            controller.endTurn();
        } else if (choice == null) {
            this.controller.endTurn();
        } else {

            switch (choice.getDescription()) {


                //sets the state to moving and sets the range accordingly
                case ActionBuilder.MOVE:
                    this.controller.setCurrentState(this.controller.moving);
                    ((Moving) this.controller.moving).setRange(choice.getRange());
                    this.controller.currentState.executeState();
                    break;

                //sets the state to picking and sets the range accordingly
                case ActionBuilder.PICK:
                    this.controller.setCurrentState(this.controller.picking);
                    ((Picking) this.controller.picking).setRange(choice.getRange());
                    this.controller.currentState.executeState();
                    break;

                //sets the range to shooting and sets the range accordingly
                case ActionBuilder.SHOOT:
                    this.controller.setCurrentState(this.controller.choosingWeapon);
                    ((Shooting) this.controller.shooting).setRange(choice.getRange());
                    this.controller.currentState.executeState();
                    break;

                //sets the range to choosing power up
                case ActionBuilder.POWER_UP:
                    this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
                    this.controller.currentState.executeState();
                    break;

                //sets the state to reloading
                case ActionBuilder.RELOAD:
                    this.controller.setCurrentState(this.controller.reloading);
                    this.controller.currentState.executeState();
                    break;

                //sets the state to special weird, useless and complicated frenzy action
                case ActionBuilder.FRENZY_SHOOT:
                    this.controller.setCurrentState(this.controller.frenzySpecialAction);
                    ((FrenzyShooting)this.controller.frenzySpecialAction).setRange(choice.getRange());
                    this.controller.currentState.executeState();
                    break;

                //sets the state to discarding a Power Up
                case ActionBuilder.DISCARD_POWER_UP:
                    this.controller.setCurrentState(this.controller.discardingPowerUp);
                    this.controller.currentState.executeState();
                    break;

                //sets the state to using the bot
                case ActionBuilder.USE_BOT:
                    this.controller.setCurrentState(this.controller.asBot);
                    this.controller.currentState.executeState();
                    break;

                //ends the turn
                case ActionBuilder.OVER:
                    this.controller.endTurn();
                    break;

            }
        }

    }


}

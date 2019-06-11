package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.actions.ActionBuilder;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ChoosingMove implements ControllerState{

    private Controller controller;
    private int movesLeft;
    private ArrayList<Action> options;

    public ChoosingMove(Controller controller) {
        this.controller = controller;
    }

    /**
     * Sets the controller state to the corresponding action the player chooses
     */

    @Override
    public void command() {


        this.options = new ArrayList<>();

        options = ActionBuilder.build(controller.getCurrentPlayer(), this.controller.getModel().isFrenzy());

        Action choice = this.controller.getView().showActions(options);

        if(this.controller.getCurrentPlayer().isDead()){

            this.controller.setCurrentState(this.controller.spawning);
            this.controller.getCurrentState().command();

        }

        switch (choice.getDescription()){

                //sets the state to moving and sets the range accordingly
            case "Move":
                this.controller.setCurrentState(this.controller.moving);
                ((Moving)this.controller.moving).setRange(choice.getRange());
                this.controller.currentState.command();

                //sets the state to picking and sets the range accordingly
            case "Pick":
                this.controller.setCurrentState(this.controller.picking);
                ((Picking)this.controller.moving).setRange(choice.getRange());
                this.controller.currentState.command();

                //sets the range to shooting and sets the range accordingly
            case "Shoot":
                this.controller.setCurrentState(this.controller.choosingWeapon);
                ((Shooting)this.controller.shooting).setRange(choice.getRange());
                this.controller.currentState.command();

                //sets the range to choosing power up
            case "PowerUp":
                this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
                this.controller.currentState.command();

                //sets the state to reloading
            case "Reload":
                this.controller.setCurrentState(this.controller.reloading);
                this.controller.currentState.command();

                //sets the state to special weir useless and complicated frenzy action
            case "Move, Reload and Shoot":
                this.controller.setCurrentState(this.controller.frenzySpecialAction);
                this.controller.currentState.command();

                //ends the turn
            case "End Turn":
                this.controller.endTurn();

        }

    }



}

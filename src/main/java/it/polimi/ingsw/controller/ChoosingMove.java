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

    @Override
    public void command() {

        this.options = new ArrayList<>();

        options = ActionBuilder.build(controller.getCurrentPlayer(), this.controller.getModel().isFrenzy());

        Action choice = this.controller.getView().showMoves(options);

        switch (choice.getDescription()){
            case "Move":
                this.controller.setCurrentState(this.controller.moving);
                ((Moving)this.controller.moving).setRange(choice.getRange());
            case "Pick":
                this.controller.setCurrentState(this.controller.picking);
                ((Picking)this.controller.moving).setRange(choice.getRange());
            case "Shoot":
                this.controller.setCurrentState(this.controller.choosingWeapon);
                ((Shooting)this.controller.shooting).setRange(choice.getRange());
            case "PowerUp":
                this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
            case "Reload":
                this.controller.setCurrentState(this.controller.reloading);
            case "Move, Reload and Shoot":
                this.controller.setCurrentState(this.controller.frenzySpecialAction);
        }

    }



}

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

    public ChoosingMove(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void shoot() {

    }

    @Override
    public void chooseWeapon() {

    }

    @Override
    public void move() {

    }

    @Override
    public void pick() {

    }

    @Override
    public void choose() {

        Set<Action> availableActions = new HashSet<>();

        if (this.controller.getModel().getMovesLeft() != 0) {
            availableActions = ActionBuilder.build(this.controller.getModel().getCurrentPlayer(), this.controller.getModel().isFrenzy(),
                    this.controller.getMovesLeft());
        }

        Set<String> options = new HashSet<>();

        for (Action action : availableActions) {
            options.add(action.getDescription());
        }

        if (!this.controller.getCurrentPlayer().getPowerupList().isEmpty()) {
            options.add("usePU");
        }

        String choice = this.controller.getView().showMoves(options);

        switch (choice) {
            case "UsePU":
                this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
            case "Move":
                this.controller.setCurrentState(this.controller.moving);
            case "Shoot":
                this.controller.setCurrentState(this.controller.shooting);
            case "Reload":
                this.controller.setCurrentState(this.controller.reloading);
        }

    }

    @Override
    public void chooseMove() {

    }

    @Override
    public void discardPU() {

    }

    @Override
    public void discardWeapon() {

    }

    @Override
    public void usePU() {

    }

    @Override
    public void teleport(Square teleportHere) {

    }

    @Override
    public void tagback() {

    }

    @Override
    public void Scope(PowerUp choice) {

    }

    @Override
    public void Newton(PowerUp choice) {

    }

    @Override
    public void useNewton(Figure Target, Square moveTo) {

    }
}

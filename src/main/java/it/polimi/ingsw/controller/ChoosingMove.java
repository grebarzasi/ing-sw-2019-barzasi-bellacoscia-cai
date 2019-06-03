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

    public ChoosingMove(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

        Set<Action> availableActions = new HashSet<>();


        availableActions = new HashSet<>();


        Set<String> options = new HashSet<>();

        for (Action action : availableActions) {
            options.add(action.getDescription());
        }

        if (!this.controller.getCurrentPlayer().getPowerupList().isEmpty()) {
            options.add("usePU");
        }

        if (this.controller.getModel().getBot() != null) {
            options.add("UseBot");
        }


        options.add("end");
        options.add("reload");

        String choice = this.controller.getView().showMoves(options);

        switch (choice) {
            case "UsePU":
            case "PowerUp":
            case "usepowerup":
            case "usePU":
            case "pu":
            case "PU":
                this.controller.setCurrentState(this.controller.choosingPowerUpToUse);
            case "Move":
                this.controller.setCurrentState(this.controller.moving);
            case "Shoot":
                this.controller.setCurrentState(this.controller.shooting);
            case "Reload":
                this.controller.setCurrentState(this.controller.reloading);
            case "Pick":
                this.controller.setCurrentState(this.controller.picking);
            case "UseBot":
                this.controller.setCurrentState(this.controller.asBot);
            case "end":
                this.controller.endGame();
        }

    }


}

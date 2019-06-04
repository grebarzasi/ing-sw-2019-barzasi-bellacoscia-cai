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

    }
}

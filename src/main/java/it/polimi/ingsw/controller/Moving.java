package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Moving implements ControllerState{

    private Controller controller;

    public Moving(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

    }

}

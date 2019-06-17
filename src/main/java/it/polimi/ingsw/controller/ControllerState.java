package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.io.IOException;
import java.util.ArrayList;

/**
 * State pattern interface
 */

public interface ControllerState {

    /**
     * Controller's interaction method, interacts with the user through the view interface
     */

    void command() throws IOException ;
}


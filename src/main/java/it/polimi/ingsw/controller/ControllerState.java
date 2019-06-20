package it.polimi.ingsw.controller;

/**
 * State pattern interface
 */

public interface ControllerState {

    /**
     * Controller's interaction method, interacts with the user through the view interface
     */

    void executeState();

}


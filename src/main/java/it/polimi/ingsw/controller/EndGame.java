package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;

/**
 * Controller state of the endgame status
 */

public class EndGame implements ControllerState {

    private Controller controller;

    public EndGame(Controller controller) {
        this.controller = controller;
    }

    /**
     * Displayes the leaderboard to all the players
     */

    @Override
    public void executeState() {

        for(Player p: this.controller.getModel().getPlayerList()){

            p.getView().displayLeaderboard();
            p.getView().displayMessage("GAME IS FUCKING OVER BOYS");

        }

        System.out.println("GAME IS FUCKING OVER BOYS");

    }


}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;

public class EndGame implements ControllerState {

    private Controller controller;

    public EndGame(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void executeState() {

        for(Player p: this.controller.getModel().getPlayerList()){

            p.getView().displayLeaderboard();

        }
    }

}

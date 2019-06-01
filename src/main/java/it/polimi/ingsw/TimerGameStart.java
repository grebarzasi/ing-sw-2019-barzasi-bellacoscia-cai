package it.polimi.ingsw;

import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class TimerGameStart extends TimerTask {
    private Lobby lobby;
    public TimerGameStart(Lobby lobby){
        this.lobby=lobby;
    }
    @Override
    public void run() {
        lobby.setHasStarted(true);
        //lobby.updateClients();
        System.out.println("Game Started");
    }
}

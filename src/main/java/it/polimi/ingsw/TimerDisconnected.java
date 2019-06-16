package it.polimi.ingsw;

import it.polimi.ingsw.connection.ClientHandler;

import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class TimerDisconnected extends TimerTask {
    private ClientHandler cl;
    public TimerDisconnected(ClientHandler cl){
        this.cl=cl;
    }
    @Override
    public void run() {
        cl.setDisconnected(true);
    }
}

package it.polimi.ingsw;

import it.polimi.ingsw.connection.socket.SClientHandler;

import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class TimerDisconnected extends TimerTask {
    private SClientHandler cl;
    public TimerDisconnected(SClientHandler cl){
        this.cl=cl;
    }
    @Override
    public void run() {
        cl.setDisconnected(true);
    }
}

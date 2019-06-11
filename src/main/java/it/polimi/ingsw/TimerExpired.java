package it.polimi.ingsw;

import it.polimi.ingsw.connection.socket.SClientHandler;

import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class TimerExpired extends TimerTask {
    private SClientHandler cl;
    public TimerExpired(SClientHandler cl){
        this.cl=cl;
    }
    @Override
    public void run() {
        cl.setExpired(true);
    }
}

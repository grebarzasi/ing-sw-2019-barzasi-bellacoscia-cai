package it.polimi.ingsw;

import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.socket.SocketClientHandler;

import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class TimerExpired extends TimerTask {

    private ClientHandler cl;

    public TimerExpired(SocketClientHandler cl){
        this.cl=cl;
    }

    @Override
    public void run() {
        cl.setExpired(true);
    }

}

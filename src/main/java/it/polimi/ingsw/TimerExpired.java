package it.polimi.ingsw;

import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.socket.SocketClientHandler;

import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class TimerExpired extends Thread {

    int time;

    public TimerExpired(int time){
        this.time=time;
    }

    @Override
    public void run() {

    }

}

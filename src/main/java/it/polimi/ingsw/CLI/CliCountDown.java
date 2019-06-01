package it.polimi.ingsw.CLI;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.TimerGameStart;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gregorio Barzasi
 */
public class CliCountDown extends Thread {
    private int time;
    public CliCountDown(int time){
        this.time=time;
    }
    @Override
    public void run() {
        System.out.println("Countdown started!");
        while(time>0){
            System.out.println(time + "s left");
            time=time-10;
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GAME STARTED");

    }
}

package it.polimi.ingsw.CLI;

import static it.polimi.ingsw.CLI.CliMessages.*;

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
        System.out.println(COUNTDOWN_START);
        while(time>0){
            System.out.println(time + "s left");
            time=time-10;
            try {
                Thread.sleep(10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(GAME_START);

    }
}

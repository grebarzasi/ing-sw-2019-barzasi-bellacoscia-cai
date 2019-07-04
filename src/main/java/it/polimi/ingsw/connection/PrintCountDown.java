package it.polimi.ingsw.connection;

import static it.polimi.ingsw.view.command_line_view.CliMessages.*;

/**
 * prints the count down when you're in waiting room
 *
 * @author Gregorio Barzasi
 */
public class PrintCountDown extends Thread {
    private int time;
    public PrintCountDown(int time){
        this.time=time;
    }
    @Override
    public void run() {
        System.out.println(COUNTDOWN_START);
        while(time>0){
            System.out.println(time + "s left");
            time=time-5;
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println(GAME_START);

    }
}

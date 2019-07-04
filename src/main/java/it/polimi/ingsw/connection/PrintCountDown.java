package it.polimi.ingsw.connection;

import static it.polimi.ingsw.Color.RESET;

/**
 * prints the count down when you're in waiting room
 *
 * @author Gregorio Barzasi
 */
public class PrintCountDown extends Thread {

    public static final String COUNTDOWN_START = "Countdown avviato!"+RESET;
    public static final String GAME_START = "Gioco Iniziato!"+RESET;

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

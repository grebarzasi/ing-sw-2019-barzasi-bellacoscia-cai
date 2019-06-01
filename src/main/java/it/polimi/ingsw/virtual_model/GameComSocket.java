package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.socket.SServer;

import java.io.IOException;

/**
 * @author Gregorio Barzasi
 */
public class GameComSocket extends Thread{
    private SClient s;

    public GameComSocket(SClient s){
        this.s=s;
    }

    public void listen(){
        String msg;
        try {
        while(true){

            msg= s.getInput().readLine();


            switch (msg){
                case "test":

            }

            break;

        }
        } catch (IOException e) {
        e.printStackTrace();
    }
    }
}

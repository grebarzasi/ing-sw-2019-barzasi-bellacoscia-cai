package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.connection.socket.SClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static it.polimi.ingsw.connection.ConnMessage.*;

/**
 * @author Gregorio Barzasi
 */
public class GameComSocket extends Thread{
    private SClient s;
    public PrintWriter o;
    public BufferedReader i;
    private ViewClient v;

    public GameComSocket(SClient s){
        this.s=s;
        this.o=s.getOutput();
        this.i=s.getInput();
    }

    public void listen(){
        String msg;
        String args;
        try {
        while(true){

            msg= i.readLine();

            switch (msg){
                case PING:
                    o.println(PONG);
                    break;
                case SHOW_PU:
                    args=i.readLine();
                    o.println(v.showPowerUp(parseString(args)));
                    break;
                case SHOW_WEAPONS:
                    args=i.readLine();
                    o.println(v.showWeapon(parseString(args)));
                    break;
                case SHOW_ACTIONS:
                    args=i.readLine();
                    o.println(v.showAction(parseString(args)));
                    break;
                case SHOW_MOVES:
                    args=i.readLine();
                    o.println(v.showPossibleMoves(parseString(args)));
                    break;
                case SHOW_TARGETS:
                    args=i.readLine();
                    o.println(v.showTargets(parseString(args)));
                    break;
                case CHOOSE_DIRECTION:
                    o.println(v.chooseDirection());
                    break;
                case UPDATE:
                    args=i.readLine();
                    v.updateModel(args);
                    break;
            }

            break;

        }
           } catch (IOException e) {
               e.printStackTrace();
        }
    }

    //Parse args info
    public ArrayList<String> parseString(String args){
        ArrayList<String> sList = new ArrayList<>();
        if(args.equals(NOTHING)) {
            return sList;
        }
        String[] s=args.split(INFO_SEP);
        for(String x : s){
            sList.add(x);
        }
        return sList;
    }

    @Override
    public void run() {
        listen();
    }
}

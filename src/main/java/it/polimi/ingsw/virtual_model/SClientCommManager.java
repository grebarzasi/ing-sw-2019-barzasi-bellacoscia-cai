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
public class SClientCommManager extends Thread{
    private SClient s;
    public PrintWriter o;
    public BufferedReader i;
    private ViewClient v;
    private boolean goOn=true;

    public SClientCommManager(SClient s,ViewClient v){
        this.s=s;
        this.o=s.getOutput();
        this.i=s.getInput();
        this.v=v;
    }

    public void listen(){
        String msg;
        String args;
        try {
        while(goOn){
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            msg= i.readLine();
            System.out.println(msg);

            switch (msg){
                case PING:
                    o.println(PONG);
                    break;
                case SHOW_PU:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showPowerUp(parseString(args)));
                    break;
                case SHOW_WEAPONS:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showWeapon(parseString(args)));
                    break;
                case SHOW_ACTIONS:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showActions(parseString(args)));
                    break;
                case SHOW_MOVES:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showPossibleMoves(parseString(args)));
                    break;
                case SHOW_TARGETS:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showTargets(parseString(args)));
                    break;
                case CHOOSE_DIRECTION:
                    o.println(AKN);
                    o.println(v.chooseDirection());
                    break;
                case SHOW_SINGLE_TARGET:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.singleTargetingShowTarget(parseString(args)));
                    break;
                case SHOW_MESSAGE:
                    o.println(AKN);
                    args=i.readLine();
                    v.displayMessage(args);
                    o.println(AKN);
                    break;
                case SHOW_BOOLEAN:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showBoolean(args));
                    break;
                case UPDATE:
                    o.println(AKN);
                    args=i.readLine();
                    v.updateModel(args);
                    o.println(AKN);
                    break;
            }

        }
           } catch (IOException e) {
            System.err.print(CONNECTION_ERR);
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

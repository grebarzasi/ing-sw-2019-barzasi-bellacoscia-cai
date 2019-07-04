package it.polimi.ingsw.connection.client.socket;

import it.polimi.ingsw.connection.DisconnectionHandler;
import it.polimi.ingsw.ViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static it.polimi.ingsw.connection.ConnMessage.*;
/**
 * This class handle the communication between Scocket server and socket client
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
    /**
     *listen the socket input stream and does stuff based on the input
     */
    public void listen(){
        String msg;
        String args;
        try {
        while(goOn){

            msg= i.readLine();
//            if(!msg.equals(PING))
//                System.out.println("\n"+msg);
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
                case SHOW_EFFECTS:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showEffects(parseString(args)));
                    break;
                case SHOW_ACTIONS:
                    o.println(AKN);
                    args=i.readLine();
                    String rpl = v.showActions(parseString(args));
                    o.println(rpl);
                    break;
                case SHOW_MOVES:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showPossibleMoves(parseString(args)));
                    break;
                case CHOOSE_DIRECTION:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.chooseDirection(parseString(args)));
                    break;
                case SHOW_TARGET_ADV:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(v.showTargetAdvanced(parseString(args)));
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
                case DISPLAY_LEADERBOARD:
                    o.println(AKN);
                    args=i.readLine();
                    o.println(AKN);
                    //time consuming
                    v.displayLeaderboard(parseString(args));
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
            DisconnectionHandler.generic();
        }
    }

    /**
     *parse string info into an array list
     */
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

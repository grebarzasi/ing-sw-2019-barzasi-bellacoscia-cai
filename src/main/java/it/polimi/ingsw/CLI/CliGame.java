package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.virtual_model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import static it.polimi.ingsw.CLI.CLiBoardStuff.ALL_AMMO;
import static it.polimi.ingsw.CLI.CLiBoardStuff.ALL_CHARACTERS;
import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.CLI.CliMessages.*;
import static it.polimi.ingsw.connection.ConnMessage.*;
import static java.lang.Thread.sleep;

public class CliGame implements ViewClient {

    private BufferedReader sc=new BufferedReader(new InputStreamReader(System.in));
    private CliBoard board;
    private UpdateParser parser;
    private ConnectionTech c;

    public CliGame(ConnectionTech c,VirtualPlayer p){
        this.board=new CliBoard(new VirtualModel(p));
        this.parser=new UpdateParser(board.getModel());
        this.c=c;
    }

    public void gameStart(){
        if(!c.isRmi()) {
            ((SClient) c).setCommManager(new SClientCommManager(((SClient) c), this));
            ((SClient) c).getCommManager().start();
        }
        System.out.print("\nWaiting for board to deploy\n");
    }

    public String genericChoice(ArrayList<String> args,String q, String error, boolean printArgs) {
        int i =1;
        String temp[];
        int reply=0;
        do {
            i=1;
            System.out.print(RESET);
            if(printArgs) {
                for (String s : args) {
                    temp = s.split(INNER_SEP);
                    System.out.print(i + " - " + temp[0]);
                    if (ALL_AMMO.contains(temp[1])) {
                        System.out.print("<");
                        board.colorizeCP(temp[1]);
                        System.out.println(">");
                    } else
                        System.out.println();
                    i++;
                }
            }
            System.out.print(RESET+q+"\n");
            reply=chooseFromArray(args,error);
        } while (reply==0);
        if(reply==-1)
            return NOTHING;
        return args.get(reply-1);
    }

    public int chooseFromArray(ArrayList<String>args, String err){
        String s ="";
        int reply=0;
        try {
            while(sc.ready()) {
                sc.read();
            }
            s = sc.readLine();
            if(s.equals("<"))
                return -1;
            try {
                reply = Integer.parseInt(s);
            }catch(NumberFormatException e){
                reply=0;
            }
            if (reply<=0 || args.size()<(reply)) {
                System.out.println(err);
                reply=0;
            }
        } catch (IOException e) {
        e.printStackTrace();
    }
        return reply;
    }
    /**
     * Shows the PowerUps and makes the user command one, then returns it
     *
     * @param args the Powerups to show
     * @return the chosen one
     */
    public String showPowerUp(ArrayList<String> args) {
        return genericChoice(args,CHOOSE_PU_Q,CHOOSE_PU_ERR,true);
    }

    /**
     * Shows the Weapons and makes the user command one, the returns is
     *
     * @param args the Weapons to show
     * @return the chosen one
     */
    public String showWeapon(ArrayList<String> args) {
        int i =1;
        String temp[];
        int reply=0;
        do {
            System.out.print(RESET+"\n");
                for (String s : args) {
                    temp = s.split(INNER_SEP);
                    System.out.print(i + " - " + temp[0]+"[");
                    board.colorizeCP(temp[1]);
                    System.out.print("]");
                    if(temp.length>2)
                        board.colorizeCP(temp[2]);
                    i++;
                }
            System.out.print(RESET+CHOOSE_WP_Q+"\n");
            reply=chooseFromArray(args,CHOOSE_WP_ERR);
        } while (reply==0);
        if(reply==-1)
            return NOTHING;
        return args.get(reply-1);
    }

    /**
     * Show the possible moves that a player can perform then makes the user command one,
     * then returns it
     *
     * @param args the moves to show
     * @return the chosen one
     */
    public String showActions(ArrayList<String> args) {
        return genericChoice(args,CHOOSE_ACTION_Q,CHOOSE_ACTION_ERR,true);
    }

    /**
     * Shows the possible destinations a figure can reach and then returns the chosen value
     *
     * @param args the possible destinations
     * @return the chosen one
     */
    public String showPossibleMoves(ArrayList<String> args) {
        String reply="";
        String[] temp;
        HashMap<String,String> column=new HashMap<>();
        column.put("A","0");
        column.put("B","1");
        column.put("C","2");
        column.put("D","3");
        HashMap<String,String> row=new HashMap<>();
        row.put("1","0");
        row.put("2","1");
        row.put("3","2");
        row.put("4","3");

        do {
            if (args.get(0).equals("true")) {
                args.remove(0);
                printCells(args);
            }
            System.out.println(WHITE+CHOOSE_SQUARE_Q);
            try {
                reply = sc.readLine();
                if(reply.equals("<"))
                    return NOTHING;
            } catch (IOException e) {
                e.printStackTrace();
            }
            reply=reply.toUpperCase();
            temp=reply.split(INNER_SEP);
            if(reply.isEmpty()||(temp.length==2&&row.containsKey(temp[0])&&column.containsKey(temp[1])))
                reply=row.get(temp[0])+INNER_SEP+column.get(temp[1]);
            else {
                System.out.println(CHOOSE_SQUARE_ERR);
                reply = "";
            }
        }while (reply.isEmpty()||!args.contains(reply));

        return reply;
    }

    public void printCells(ArrayList<String> args) {
        HashMap<String,String> orizontal=new HashMap<>();
        orizontal.put("0","A");
        orizontal.put("1","B");
        orizontal.put("2","C");
        orizontal.put("3","D");
        HashMap<String,String> vertical=new HashMap<>();
        vertical.put("0","1");
        vertical.put("1","2");
        vertical.put("2","3");
        vertical.put("3","4");

        String[] temp;
        for(String s: args){
            temp=s.split(INNER_SEP);
            System.out.print(vertical.get(temp[0])+INNER_SEP+orizontal.get(temp[1]));
            System.out.println();
        }

    }

    /**
     * Shows the valid targets a Figure can target, then returns the targets the user decides
     * to hit
     *
     * @param args the valid targets
     * @return the chosen targets
     */
    public String showTargets(ArrayList<String> args) {
        return genericChoice(args,CHOOSE_TARGET_Q,CHOOSE_TARGET_ERR,true);
    }

    /**
     * returns the chosen one direction
     *
     * @return the chosen one
     */
    public String chooseDirection() {
        return null;
    }

    /**
     * Displays a message and makes the user make a boolean choice
     *
     * @param message the question's message
     * @return the user's choice
     */
    public boolean showBoolean(String message) {
        return false;
    }

    /**
     * Displays a message to the user
     *
     * @param message the message
     */
    public void displayMessage(String message) {

    }

    /**
     * Displays the leaderboard of the game to the user
     */
    public void displayLeaderboard() {

    }

    public String showEffects(Set<String> args) {
        return null;
    }

    public void updateModel(String message) {
        parser.updateModel(message);
        clearScreen();
        board.draw();
    }

    /**
     * Show the possible moves that a player can perform then makes the user choose one,
     * then returns it
     *
     * @param args the moves to show
     * @return the chosen one
     */
    /**
     * Displays a message and makes the user make a boolean choice
     *
     * @param args
     * @return the user's choice
     */
    @Override
    public String singleTargetingShowTarget(ArrayList<String> args) {
        return null;
    }
}

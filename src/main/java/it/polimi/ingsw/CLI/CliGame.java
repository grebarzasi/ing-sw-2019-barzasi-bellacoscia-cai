package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.virtual_model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.CLI.CliMessages.*;
import static it.polimi.ingsw.connection.ConnMessage.*;
import static java.lang.Thread.sleep;

public class CliGame implements ViewClient {

    private BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

    private CliBoard board;
    private UpdateParser parser;
    private ConnectionTech c;

    public CliGame(ConnectionTech c,VirtualPlayer p){
        this.board=new CliBoard(new VirtualModel(p));
        this.parser=new UpdateParser(board.getModel());
        this.c=c;
    }

    public void gameStart(){
        if(!c.isRmi())
            ((SClient)c).setCommManager(new SClientCommManager(((SClient)c),this));
        ((SClient)c).getCommManager().start();
        System.out.print("\nWaiting for board to deploy\n");
    }

    public String genericChoice(ArrayList<String> args,String q, String error) {
        int i =1;
        String temp[];
        int reply=0;
        do {
            System.out.print(RESET+"\n");
            for(String s: args) {
                temp = s.split(INNER_SEP);
                System.out.println(i + " - " + temp[0]);
                i++;
            }
            System.out.print(RESET+q+"\n");
            reply=chooseFromArray(args,error);
        } while (reply==0);
        return args.get(reply-1);
    }

    public int chooseFromArray(ArrayList<String>args, String err){
        String s ="";
        int reply=0;
        int i=1;
        for(String k : args){
            System.out.println(i+"- "+k);
            i++;
        }
        try {
            s = sc.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reply = Integer.parseInt(s);
        }catch(NumberFormatException e){
            reply=0;
        }
        if (reply<=0 || args.size()<(reply)) {
            System.out.println(err);
            reply=0;
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
        int i =1;
        String temp[];
        int reply=0;
        do {
            System.out.print(RESET+CHOOSE_PU_Q+"\n");
            reply=chooseFromArray(args,CHOOSE_PU_ERR);
        } while (reply==0);

        return args.get(reply-1);
    }

    /**
     * Shows the Weapons and makes the user command one, the returns is
     *
     * @param args the Weapons to show
     * @return the chosen one
     */
    public String showWeapon(ArrayList<String> args) {
        return genericChoice(args,CHOOSE_WP_Q,CHOOSE_WP_ERR);
    }

    /**
     * Show the possible moves that a player can perform then makes the user command one,
     * then returns it
     *
     * @param args the moves to show
     * @return the chosen one
     */
    public String showAction(ArrayList<String> args) {
        return genericChoice(args,CHOOSE_ACTION_Q,CHOOSE_ACTION_ERR);
    }

    /**
     * Shows the possible destinations a figure can reach and then returns the chosen value
     *
     * @param args the possible destinations
     * @return the chosen one
     */
    public String showPossibleMoves(ArrayList<String> args) {
        return null;
    }

    /**
     * Shows the valid targets a Figure can target, then returns the targets the user decides
     * to hit
     *
     * @param args the valid targets
     * @return the chosen targets
     */
    public String showTargets(ArrayList<String> args) {
        return null;
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
    @Override
    public String showActions(ArrayList<String> args) {
        return null;
    }

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

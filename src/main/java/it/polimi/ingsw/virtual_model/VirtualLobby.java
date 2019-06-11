package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static it.polimi.ingsw.connection.ConnMessage.INFO_SEP;

/**
 * This class represent a virtual version of server-side lobby
 * @author Gregorio Barzasi
 */
public class VirtualLobby {
    private ConnectionTech conn;
    private HashMap<String,VirtualPlayer> players;
    private ArrayList<VirtualPlayer> newPlayersList;
    private VirtualPlayer owner;
    private int mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;

    private boolean gameStarted;
    private boolean gameTimerStarted;


    public VirtualLobby(ConnectionTech c, VirtualPlayer owner){
        this.conn=c;
        this.owner=owner;
        this.gameStarted=false;
        this.newPlayersList =new ArrayList<>();
        this.players = new HashMap<>();
    }

    /**
     * send your game preferences to server
     */
    public boolean sendPref() throws IOException {
        if (conn.isRmi()) {
            return false;
        } else {
            SClient c = ((SClient) conn);
            System.out.println("sending preferences");
            c.getOutput().println(mapPref);
            c.getOutput().println(killPref);
            c.getOutput().println(terminatorPref);
            c.getOutput().println(finalFrenzyPref);
            String reply = c.getInput().readLine();
            if(reply.equals("accepted")){
                System.out.println(reply);
                return true;
            }else{
                System.out.println("Something went wrong");
                return false;
            }
        }
    }
    /**
     * wait lobby update from server
     */


    public synchronized boolean waitUpdate()throws IOException {
        if (conn.isRmi()) {
            return false;
        } else {
            String all;
            SClient c = ((SClient) conn);
            all = c.getInput().readLine();
            if(all.equals("*started*")) {
                gameStarted = true;
                return true;
            }
            if(all.equals("*timer_started*")) {
                gameTimerStarted = true;
                return true;
            }if(all.equals("*PING*")) {
                c.getOutput().println("*PONG*");
                return true;
            }
            updatePlayers(all);
            return true;
        }
    }

    /**
     * Updates in game players info.
     */
    private synchronized void updatePlayers(String s){
        String [] allPl = s.split(INFO_SEP);
        for(String p : allPl){
            String [] plStat = p.split(",");
            if(!players.containsKey(plStat[0])){
                VirtualPlayer pla = new VirtualPlayer(plStat[0],plStat[1]);
                players.put(plStat[0],pla);
                newPlayersList.add(pla);
            }
        }
    }

    public boolean hasGameTimerStarted() {
        return gameTimerStarted;
    }
    public void setGameTimerStarted(boolean time) {
        this.gameTimerStarted=time;
    }

    public int getMapPref() {
        return mapPref;
    }

    public void setMapPref(int mapPref) {
        this.mapPref = mapPref;
    }

    public int getKillPref() {
        return killPref;
    }

    public void setKillPref(int killPref) {
        this.killPref = killPref;
    }

    public boolean isTerminatorPref() {
        return terminatorPref;
    }

    public void setTerminatorPref(boolean terminatorPref) {
        this.terminatorPref = terminatorPref;
    }

    public boolean isFinalFrenzyPref() {
        return finalFrenzyPref;
    }

    public synchronized boolean isGameStarted() {
        return gameStarted;
    }

    public void setFinalFrenzyPref(boolean finalFrenzyPref) {
        this.finalFrenzyPref = finalFrenzyPref;
    }

    public VirtualPlayer getOwner() {
        return owner;
    }

    public void setOwner(VirtualPlayer owner) {
        this.owner = owner;
    }

    public synchronized ArrayList<VirtualPlayer> getNewPlayersList() {
        return newPlayersList;
    }
    public synchronized void setNewPlayersList(ArrayList<VirtualPlayer> list) {
        this.newPlayersList = list;
    }

    public HashMap<String, VirtualPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, VirtualPlayer> players) {
        this.players = players;
    }
}

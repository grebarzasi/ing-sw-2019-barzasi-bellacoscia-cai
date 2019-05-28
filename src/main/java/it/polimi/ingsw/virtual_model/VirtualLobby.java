package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represent a virtual version of server-side lobby
 * @author Gregorio Barzasi
 */
public class VirtualLobby {
    private ConnectionTech conn;
    private HashMap<String,VirtualPlayer> players = new HashMap<>();
    private VirtualPlayer owner;
    private int mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;

    private boolean gameStarted;


    public VirtualLobby(ConnectionTech c, VirtualPlayer owner){
        this.conn=c;
        this.owner=owner;
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
     * send your game preferences to server
     */
    public void sendReady() throws IOException {
        if (conn.isRmi()) {
            return;
        } else {
            SClient c = ((SClient) conn);
            if(owner.isReady())
                c.getOutput().println("ready");
            else
                c.getOutput().println("not ready");
        }
    }


    /**
     * wait lobby update from server
     */


    public boolean waitUpdate()throws IOException {
        if (conn.isRmi()) {
            return false;
        } else {
            String all;
            SClient c = ((SClient) conn);
            all = c.getInput().readLine();
            updatePlayers(all);
            return true;
        }
    }

    /**
     * Updates in game players info.
     */
    private void updatePlayers(String s){
        String [] allPl = s.split(";");
        for(String p : allPl){
            System.out.println(p);
            String [] plStat = p.split(",");
            if(!players.containsKey(plStat[0])){
                players.put(plStat[0],new VirtualPlayer(plStat[0],plStat[1],plStat[2].equals("1")));
            }else{
                players.get(plStat[0]).setUsername(plStat[0]);
                players.get(plStat[0]).setCharacter(plStat[1]);
                players.get(plStat[0]).setReady(plStat[2].equals("1"));
            }
        }
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
    public boolean isGameStarted() {
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
}

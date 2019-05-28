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
    private ArrayList<String> newPlayersList =new ArrayList<>();
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
            String [] plStat = p.split(",");
            if(!players.containsKey(plStat[0])){
                players.put(plStat[0],new VirtualPlayer(plStat[0],plStat[1]));
                newPlayersList.add(p);
            }
            if(newPlayersList.contains(p))
                newPlayersList.remove(p);
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

    public ArrayList<String> getNewPlayersList() {
        return newPlayersList;
    }
}

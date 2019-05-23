package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.rmi.RmiClient;
import it.polimi.ingsw.connection.socket.SClient;

import java.io.IOException;
import java.util.ArrayList;

public class VirtualLobby {
    private ConnectionTech conn;
    private ArrayList<VirtualPlayer> players = new ArrayList<>();
    private VirtualPlayer owner;
    private String mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;


    public VirtualLobby(ConnectionTech c, VirtualPlayer owner){
        this.conn=c;
        this.owner=owner;
    }

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

    public boolean update(){
        return true;
    }

    public String getMapPref() {
        return mapPref;
    }

    public void setMapPref(String mapPref) {
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

    public void setFinalFrenzyPref(boolean finalFrenzyPref) {
        this.finalFrenzyPref = finalFrenzyPref;
    }
}

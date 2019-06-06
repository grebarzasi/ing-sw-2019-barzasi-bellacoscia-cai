package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.connection.ConnectionTech;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class VirtualBoard {
    private ConnectionTech conn;
    private int skull;
    private ArrayList<String> killshotTrack=new ArrayList<>();
    private VirtualMap map = new VirtualMap();



    public ConnectionTech getConn() {
        return conn;
    }

    public void setConn(ConnectionTech conn) {
        this.conn = conn;
    }

    public int getSkull() {
        return skull;
    }

    public void setSkull(int skull) {
        this.skull = skull;
    }

    public ArrayList<String> getKillshotTrack() {
        return killshotTrack;
    }

    public void setKillshotTrack(ArrayList<String> killshotTrack) {
        this.killshotTrack = killshotTrack;
    }

    public VirtualMap getMap() {
        return map;
    }

    public void setMap(VirtualMap map) {
        this.map = map;
    }

}

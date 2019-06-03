package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.connection.ConnectionTech;

import java.util.ArrayList;

public class VirtualModel {
    private ArrayList<VirtualPlayer> allPlayers;
    private VirtualBoard board;
    private ConnectionTech conn;

    public VirtualModel(ConnectionTech conn){
        this.conn=conn;
    }
    public void updateModel(){

    }
}

package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.connection.ConnectionTech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class VirtualModel {

    private ArrayList<VirtualPlayer> allPlayers;
    private VirtualBoard board;
    private ConnectionTech conn;
    private VirtualPlayer owner;
    private VirtualPlayer turn;

    public VirtualModel(ConnectionTech conn,VirtualPlayer owner){
        this.conn=conn;
        this.allPlayers = new ArrayList<>();
        this.board=new VirtualBoard();
        this.owner=owner;
    }

    public VirtualModel(ConnectionTech conn){
        this.conn=conn;
        this.allPlayers = new ArrayList<>();
        this.board=new VirtualBoard();
    }

    public VirtualModel(){
        this.allPlayers = new ArrayList<>();
        this.board=new VirtualBoard();
    }


    public VirtualPlayer findPlayer(String character){
        for (VirtualPlayer p : allPlayers){
            if(p.getCharacter().equals(character))
                return p;
        }
        return null;
    }
    public void setAllPlayers(ArrayList<VirtualPlayer> allPlayers) {
        this.allPlayers = allPlayers;
    }

    public VirtualBoard getBoard() {
        return board;
    }

    public void setBoard(VirtualBoard board) {
        this.board = board;
    }

    public ConnectionTech getConn() {
        return conn;
    }

    public void setConn(ConnectionTech conn) {
        this.conn = conn;
    }

    public ArrayList<VirtualPlayer> getAllPlayers() {
        return allPlayers;
    }

    public VirtualPlayer getOwner() {
        return owner;
    }

    public void setOwner(VirtualPlayer owner) {
        this.owner = owner;
    }

    public VirtualPlayer getTurn() {
        return turn;
    }

    public void setTurn(VirtualPlayer turn) {
        this.turn = turn;
    }
}

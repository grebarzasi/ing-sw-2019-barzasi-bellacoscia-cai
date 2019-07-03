package it.polimi.ingsw.view.virtual_model;

import java.util.ArrayList;

public class VirtualModel {

    private ArrayList<VirtualPlayer> allPlayers;
    private VirtualBoard board;
    private VirtualPlayer owner;
    private VirtualPlayer turn;
    private boolean frenzy;
    private boolean updated=false;

    public VirtualModel(VirtualPlayer owner){
        this.allPlayers = new ArrayList<>();
        this.board=new VirtualBoard();
        this.owner=owner;
        this.allPlayers.add(owner);
        updated = false;
    }



    public VirtualPlayer findPlayer(String character){
        for (VirtualPlayer p : allPlayers){
            if(p.getCharacter().equals(character))
                return p;
        }
        return null;
    }
    public VirtualBoard getBoard() {
        return board;
    }

    public void setBoard(VirtualBoard board) {
        this.board = board;
    }


    public synchronized ArrayList<VirtualPlayer> getAllPlayers() {
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

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isFrenzy() {
        return frenzy;
    }

    public void setFrenzy(boolean frenzy) {
        this.frenzy = frenzy;
    }
}

package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.AmmoLot;

public class NonSpawnSquare extends Square{

    private AmmoLot drop;


    public boolean isSpawn(){
        return false;
    }

    public NonSpawnSquare(Cell position, Room room, AmmoLot drop) {
        super(position, room);
        this.drop = drop;
    }

    public NonSpawnSquare(Cell position, Room room) {
        super(position, room);
    }

    public AmmoLot getDrop() {
        return drop;
    }

    public void setDrop(AmmoLot drop) {
        this.drop = drop;
    }
}

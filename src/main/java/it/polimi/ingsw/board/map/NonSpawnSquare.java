package it.polimi.ingsw.board.map;

import it.polimi.ingsw.cards.AmmoLot;

/**
 * A Square that does not contain a spawn point
 * contains an AmmoLot Drop
 * @author Yuting Cai
 */

public class NonSpawnSquare extends Square{

    private AmmoLot drop;


    public NonSpawnSquare(Cell position, Room room) {
        super(position, room);
    }

    public AmmoLot getDrop() {
        return drop;
    }

    @Override
    public boolean isSpawn(){
        return false;
    }

    public void setDrop(AmmoLot drop) {
        this.drop = drop;
    }
}

package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.CarePackage;

public class NonSpawnSquare extends Square{

    private CarePackage drop;

    public NonSpawnSquare(Cell position, Room room, CarePackage drop) {
        super(position, room);
        this.drop = drop;
    }

    public NonSpawnSquare(Cell position, Room room) {
        super(position, room);
    }
}

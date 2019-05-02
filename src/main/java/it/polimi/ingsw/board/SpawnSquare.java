package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * A Square that includes a respawn spot, also contains an armory where
 * players can pick a weapon
 * @author Yuting Cai
 */

public class SpawnSquare extends Square {

    private Armory armory;



    public SpawnSquare(Cell position, Room room, Armory armory) {
        super(position, room);
        this.armory = armory;
    }

    public SpawnSquare(Cell position, Room room, Square north, Square east, Square south, Square west, Armory armory) {
        super(position, room, north, east, south, west);
        this.armory = armory;
    }


    public SpawnSquare(Cell position, Room room) {
        super(position, room);
    }

    public Armory getArmory() {
        return armory;
    }

    public void setArmory(Armory armory) {
        this.armory = armory;
    }
}

package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class SpawnSquare extends Square {

    private ArrayList<Weapon> Armory;



    public SpawnSquare(Cell position, Room room, ArrayList<Weapon> armory) {
        super(position, room);
        Armory = armory;
    }

    public SpawnSquare(Cell position, Room room, Square north, Square east, Square south, Square west, ArrayList<Weapon> armory) {
        super(position, room, north, east, south, west);
        Armory = armory;
    }

    public SpawnSquare(Cell position, Room room) {
        super(position, room);
    }

    public ArrayList<Weapon> getArmory() {
        return Armory;
    }

    public void setArmory(ArrayList<Weapon> armory) {
        Armory = armory;
    }
}

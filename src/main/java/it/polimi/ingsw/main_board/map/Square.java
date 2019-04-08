package it.polimi.ingsw.main_board.map;

import it.polimi.ingsw.control.Master;
import it.polimi.ingsw.main_board.Player;

import java.util.ArrayList;
import java.util.Collection;


public class Square {

    private Master master;

    private Coordinates position;

    //the room to which the square belongs

    private Room room;

    //1: is a respawn cell with armory
    //0: is an ordinary cell with ammunition package

    private boolean isRespawn;


    //the square adjacent to this in that direction
    //in case of a wall or an edge of the map the field is NULL
    //a square belonging to another room in a direction implies the presence of a door
    //a door concept is therefore not needed

    private Square north;
    private Square east;
    private Square south;
    private Square west;


    public Square(Master master) {
        this.master = master;
    }






    //temporary constructor

    public void spawnRoom(Coordinates position, Room room, boolean isRespawn, Square north, Square east, Square south, Square west){

        this.position = position;
        this.room = room;
        this.isRespawn = isRespawn;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;

    }

    public Collection<Player> playersInSquare(Square where){
        Collection<Player> playersHere = new ArrayList<>();

        for (Player player : master.getPlayers()) {
            if (player.getPosition() == where) {
                playersHere.add(player);
            }
        }

        return playersHere;
    }

    public Room getRoom(){
        return room;
    }

    public Square getNorth() {
        return north;
    }

    public Square getEast() {
        return east;
    }

    public Square getSouth() {
        return south;
    }

    public Square getWest() {
        return west;
    }

    public Coordinates getPosition() {
        return position;
    }

    public boolean isReapawn() {
        return isRespawn;
    }
}



package it.polimi.ingsw.board;

import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 *represents a square in the game as is
 */


public class Square {


    //position of the square within the matrix

    private Cell position;

    //the room to which the square belongs

    private Room room;


    /*the square adjacent to this in that direction
    *in case of a wall or an edge of the map the field is NULL
    *a square belonging to another room in a direction implies the presence of a door
    *a door concept is therefore not needed
     */


    private Square north;
    private Square east;
    private Square south;
    private Square west;


    /*since the sides of a Square require other Squares they must be first set at a default value
    *they must be set after all squares have been instantiated
     */

    public Square(Cell position, Room room) {
        this.position = position;
        this.room = room;
        this.north = null;
        this.east = null;
        this.south = null;
        this.west = null;

    }

    //only for testing purposes
    public Square(Cell position, Room room, Square north, Square east, Square south, Square west) {
        this.position = position;
        this.room = room;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    /**
     * scans the list of players and checks whether they are on the square or not
     * @param  where the Square you want to know the players on
     * @return the list of players on the queried Square
     */

    private Collection<Player> playersInASquare(Square where, Collection<Player> playerList){

        Collection<Player> playersHere = new ArrayList<>();

        for (Player player : playerList) {
            if (player.getPosition() == where) {
                playersHere.add(player);
            }
        }

        return playersHere;
    }

    /**
     * Upgraded version of "playersInSquare"
     * @author Gregorio Barzasi
     */

    public Collection<Player> playersInSquare(Collection<Player> allP){
        return playersInASquare(this,allP);
    }

    public boolean isSpawn(){
        return true;
    }






    public boolean isAdjacent(Square s){

        return this.getNorth() == s || this.getEast() == s || this.getSouth() == s || this.getWest() == s;
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

    public Cell getPosition() {
        return position;
    }

    public void setNorth(Square north) {
        this.north = north;
    }

    public void setEast(Square east) {
        this.east = east;
    }

    public void setSouth(Square south) {
        this.south = south;
    }

    public void setWest(Square west) {
        this.west = west;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    //IMPLEMENT HERE
    public void pickItem(Player p){}

}



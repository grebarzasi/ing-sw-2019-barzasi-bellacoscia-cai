package it.polimi.ingsw.model.board.map;

import it.polimi.ingsw.model.Figure;

import java.util.ArrayList;
import java.util.Collection;

/**
 *represents a square in the game as is
 * @author Yuting Cai
 */


public abstract class Square {


    /**
     * Position of the square within the matrix
     */

    private Cell position;

    /**
     * the room of the square
     */

    private Room room;


    /**
    * The square adjacent to this square's north
    * in case of a wall or an edge of the map the field is NULL
    * a square belonging to another room in a direction implies the presence of a door
    * a door concept is therefore not needed
    */
    private Square north;

    /**
     * The square adjacent to this square's east
     * in case of a wall or an edge of the map the field is NULL
     * a square belonging to another room in a direction implies the presence of a door
     * a door concept is therefore not needed
     */
    private Square east;

    /**
     * The square adjacent tto this square's south
     * in case of a wall or an edge of the map the field is NULL
     * a square belonging to another room in a direction implies the presence of a door
     * a door concept is therefore not needed
     */
    private Square south;

    /**
     * The square adjacent to this square's west
     * in case of a wall or an edge of the map the field is NULL
     * a square belonging to another room in a direction implies the presence of a door
     * a door concept is therefore not needed
     */
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

    /**
     * scans the list of players and checks whether they are on the square or not
     * @return the list of players on the queried Square
     */

    public Collection<Figure> playersInSquare(Collection<Figure> figureList){

        Collection<Figure> playersHere = new ArrayList<>();

        for (Figure figure : figureList) {
            if (figure.getPosition() == this) {
                playersHere.add(figure);
            }
        }

        return playersHere;
    }

    /**
     * Checks if another square is adjacent to this one
     * @param s the inquired Square
     * @return true if s is adjacent, false otherwise
     */
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


    public boolean isSpawn(){
        return false;
    }





}



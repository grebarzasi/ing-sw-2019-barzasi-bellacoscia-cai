package it.polimi.ingsw.model.board.map;

import it.polimi.ingsw.model.Figure;

import java.util.*;

/**
 * A collection of squares, does not contain information of the squares to avoid redundancy since
 * squares have a room field.
 * @author Yuting Cai
 */

public class Room {

    /**
     * Identifier for an empty cell
     */
    public static final String VOID = "black";

    /**
     * the color of the room
     */
    private String color;

    public Room(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(color, room.color);
    }


    /**
     * @return a collection of all player in this room
     * @author Gregorio Barzasi
     */
    public Collection<Figure> playersInRoom(Collection<Figure> figureList){

        Collection<Figure> playersHere = new HashSet<>();

        for (Figure figure : figureList) {
            if (figure.getPosition()!= null && figure.getPosition().getRoom().equals(this)) {
                playersHere.add(figure);
        }
    }

        return playersHere;
    }

}



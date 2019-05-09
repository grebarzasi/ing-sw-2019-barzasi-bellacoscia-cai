package it.polimi.ingsw.board.map;

import it.polimi.ingsw.Figure;

import java.util.*;

/**
 * A collection of squares, does not contain information of the squares to avoid redundancy since
 * squares have a room field.
 * @author Yuting Cai
 */

public class Room {


    private String color;




    public void setColor(String color) {
        this.color = color;
    }

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

        Collection<Figure> playersHere = new HashSet<Figure>();

        for (Figure figure : figureList) {
            if (figure.getPosition().getRoom().equals(this)) {
                playersHere.add(figure);
            }
        }

        return playersHere;
    }

}


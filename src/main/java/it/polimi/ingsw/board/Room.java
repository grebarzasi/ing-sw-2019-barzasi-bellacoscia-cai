package it.polimi.ingsw.board;

import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Player;

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
    public Collection<Player> playersInRoom(Collection<Player> playerList){

        Collection<Player> playersHere = new HashSet<Player>();

        for (Player player : playerList) {
            if (player.getPosition().getRoom().equals(this)) {
                playersHere.add(player);
            }
        }

        return playersHere;
    }

}



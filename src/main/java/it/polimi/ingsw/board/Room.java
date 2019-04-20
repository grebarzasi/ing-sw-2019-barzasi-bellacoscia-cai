package it.polimi.ingsw.board;

import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

}



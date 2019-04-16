package it.polimi.ingsw.board;

import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Player;

import java.util.ArrayList;
import java.util.Collection;

public class Room {


    private GameControllerServer controller;

    public void setColor(String color) {
        this.color = color;
    }

    private String color;

    public Room(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}



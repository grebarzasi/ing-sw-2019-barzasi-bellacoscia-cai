package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;

import java.util.ArrayList;


public class Action {


    //Type of action
    private String description;

    //Movement a player can perform before executing this kind of action
    private int range;

    public Action(String description, int range) {
        this.description = description;
        this.range = range;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

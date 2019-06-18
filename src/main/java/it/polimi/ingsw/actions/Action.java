package it.polimi.ingsw.actions;

public class Action {

    //Type of action
    private String description;

    //Movement a player can perform before executing this kind of action
    private int range;

    Action(String description, int range) {
        this.description = description;
        this.range = range;
    }

    public String getDescription() {
        return description;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

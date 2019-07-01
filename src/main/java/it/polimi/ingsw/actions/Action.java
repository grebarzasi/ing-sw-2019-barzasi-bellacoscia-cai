package it.polimi.ingsw.actions;

public class Action {

    /**
     * Type of action
     */
    private String description;

    /**
     * Movement before performing the action
     */
    private int range;

    /**
     * Constructor taking the type and range
     * @param description The type of action that you want to create
     * @param range The range you want to create it with
     */
    Action(String description, int range) {
        this.description = description;
        this.range = range;
    }

    /**
     * @return the type of action
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the range to a given parameter
     * @param range the range you want to set it to
     */
    public void setRange(int range) {
        this.range = range;
    }
}

package it.polimi.ingsw.actions;

import it.polimi.ingsw.Player;

import java.util.ArrayList;

/**
 * Initialized by the {@link ActionBuilder} with the sequence of {@link SubAction} needed
 * and used by {@link Player} during a turn
 * @author Gregorio Barzasi
 */


public class Action {
    private Player owner;
    private String description;
    private ArrayList<SubAction> subActionArrayList;

    public Action(String description, ArrayList<SubAction> list){
        this.description=description;
        this.subActionArrayList=list;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void execute(){
        for(SubAction a :subActionArrayList){
            a.doAction(owner);
        }
    }


}

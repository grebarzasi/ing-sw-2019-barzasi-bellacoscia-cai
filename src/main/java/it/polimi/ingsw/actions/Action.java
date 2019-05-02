package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;

import java.util.ArrayList;

/**
 * Initialized by the {@link ActionBuilder} with the sequence of {@link SubAction} needed
 * and used by {@link Figure} during a turn
 * @author Gregorio Barzasi
 */


public class Action {
    private Figure owner;
    private String description;
    private ArrayList<SubAction> subActionArrayList;

    public Action(String description, ArrayList<SubAction> list){
        this.description=description;
        this.subActionArrayList=list;
    }

    public void setOwner(Figure owner) {
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

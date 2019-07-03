package it.polimi.ingsw.view.virtual_model;

import java.util.HashMap;

public class VirtualMap {
    private String name;
    private HashMap<String, VirtualCell> cells = new HashMap<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, VirtualCell> getCells() {
        return cells;
    }

    public void setCells(HashMap<String, VirtualCell> cells) {
        this.cells = cells;
    }
}

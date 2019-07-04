package it.polimi.ingsw.view.virtual_model;

import java.util.HashMap;
/**
* Map info client side
 *
 */
public class VirtualMap {
    /**
     *name of the map
     */
    private String name;
    /**
     *cell map itself
     */
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

}

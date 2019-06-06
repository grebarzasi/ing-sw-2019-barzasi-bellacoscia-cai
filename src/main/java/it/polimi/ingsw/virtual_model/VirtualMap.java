package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

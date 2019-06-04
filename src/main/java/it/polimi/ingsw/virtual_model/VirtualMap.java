package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class VirtualMap {
    private String name;
    private HashMap<String, VirtualCell> cells = new HashMap<>();



    public void parseMap(JsonNode node) {

        //parse pU cells
        parseCell(node.path("cells_pu"),false);
        //parse armory cells
        parseCell(node.path("cells_armory"),true);

    }


    public void parseCell(JsonNode node,boolean armory) {

        Iterator<String> cellsIterator = node.fieldNames();
        while (cellsIterator.hasNext()) {
            String cell = cellsIterator.next();
            if(cells.containsKey(cell))
                cells.get(cell).setContent(node.path(cell).asText());
            else
                cells.put(cell,new VirtualCell(node.path(cell).asText(),armory));
        }
    }

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

package it.polimi.ingsw.virtual_model;

import com.fasterxml.jackson.databind.JsonNode;
import it.polimi.ingsw.connection.ConnectionTech;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class VirtualBoard {
    private ConnectionTech conn;
    private int skull;
    private ArrayList<String> killshotTrack;
    private VirtualMap map = new VirtualMap();

    public void parseBoard(JsonNode node){
        skull=node.path("skull").asInt();

        //parse killshotTrack
        ArrayList<String> kills = new ArrayList<>();
        Iterator<JsonNode> killIter = node.path("killshot_track").elements();
        while (killIter.hasNext())
            kills.add(killIter.next().toString().replace("\"", ""));
        killshotTrack=kills;

        //parse cells
        map.parseMap(node.path("cells"));

    }
}

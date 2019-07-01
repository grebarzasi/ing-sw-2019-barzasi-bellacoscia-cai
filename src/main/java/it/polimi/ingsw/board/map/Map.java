package it.polimi.ingsw.board.map;


import java.util.ArrayList;

import static it.polimi.ingsw.board.map.MapLoader.loadTerrain;


/**
 * The Map of the game
 */

public class Map {

    /**
     * The matrix containing all the squares that form the map
     */
    private Square[][] squareMatrix;

    /**
     * The selected size of the map
     */
    private String name;

    //Standard sizes of a map
    public static final int WIDTH = 4;
    public static final int HEIGHT = 3;


    /**
     * Initiates the map according to the size selection
     * @param selection The map size selection
     * @author Yuting Cai
     */

    public Map(String selection) {

        //loads the map into a matrix
        this.squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];
        loadTerrain(selection, this.squareMatrix);
        this.name=selection;

    }


    public Square[][] getSquareMatrix() {
        return squareMatrix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

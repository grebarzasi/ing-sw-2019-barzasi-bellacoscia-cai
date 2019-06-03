package it.polimi.ingsw.board.map;


import java.util.ArrayList;

import static it.polimi.ingsw.board.map.MapLoader.loadTerrain;


/**
 * The Map of the game
 */

public class Map {

    private Square[][] squareMatrix;
    private String name;
    //The list of rooms in a game
    private ArrayList<Room> roomList;

    //Standard sizes of a map
    private static final int width = 4;
    private static final int height = 3;


    /**
     * Initiates the map according to the size selection
     * @param selection The map size selection
     * @author Yuting Cai
     */

    public Map(String selection) {

        //loads the map into a matrix
        this.squareMatrix = new Square[height][width];
        loadTerrain(selection, this.squareMatrix);
        this.roomList = new ArrayList<>();
        this.name=selection;

        int row;
        int column;

        //initiates the room list

        //for every square
        for( row = 0 ; row < height ; row++ ){
            for(column = 0 ; column < width ; column ++ ){

                //if its room its not in the list add it to the list
                if(!this.roomList.contains(this.squareMatrix[row][column].getRoom())){
                    this.getRoomList().add(this.squareMatrix[row][column].getRoom());
                }
            }
        }
    }


    public Square[][] getSquareMatrix() {
        return squareMatrix;
    }

    public void setSquareMatrix(Square[][] squareMatrix) {
        this.squareMatrix = squareMatrix;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

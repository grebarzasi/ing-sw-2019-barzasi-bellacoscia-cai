package it.polimi.ingsw.board;


import java.util.ArrayList;

import static it.polimi.ingsw.board.MapLoader.loadMap;

public class Map {

    private Square[][] squareMatrix;
    private ArrayList<Room> roomList;

    private static final int width = 4;
    private static final int height = 3;



    //initiates the map

    public Map(String selection) {

        this.squareMatrix = new Square[height][width];
        loadMap(selection, this.squareMatrix);
        this.roomList = new ArrayList<>();


        int row;
        int column;

        for( row = 0 ; row < height ; row++ ){
            for(column = 0 ; column < width ; column ++ ){

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
}

package it.polimi.ingsw.board;


import java.util.ArrayList;

import static it.polimi.ingsw.board.MapLoader.loadMap;

public class Map {

    private Square[][] squareMatrix;
    private ArrayList<Room> roomList;




    //initiates the map

    public void initiateMap(String selection) {

        this.squareMatrix = new Square[3][4];
        loadMap(selection, this.squareMatrix);
        this.roomList = new ArrayList<>();


        int row;
        int column;

        for( row = 0 ; row < 3 ; row++ ){
            for(column = 0 ; column < 4 ; column ++ ){

                if(!this.roomList.contains(this.squareMatrix[row][column].getRoom())){
                    this.getRoomList().add(this.squareMatrix[row][column].getRoom());
                }
            }
        }



    }

    public Map(Square[][] squareMatrix, ArrayList<Room> roomList) {
        this.squareMatrix = squareMatrix;
        this.roomList = roomList;
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

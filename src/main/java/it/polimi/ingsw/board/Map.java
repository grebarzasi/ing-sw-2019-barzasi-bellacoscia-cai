package it.polimi.ingsw.board;


import static it.polimi.ingsw.board.MapLoader.loadMap;

public class Map {

    private Square[][] squareMatrix;
    private Room[] roomList;



    //initiates the map

    public void initiateMap(String selection) {

        this.squareMatrix = new Square[3][4];
        loadMap(selection, squareMatrix);

    }


    public Map(Square[][] squareMatrix, Room[] roomList) {
        this.squareMatrix = squareMatrix;
        this.roomList = roomList;
    }



    public Square[][] getSquareMatrix() {
        return squareMatrix;
    }

    public void setSquareMatrix(Square[][] squareMatrix) {
        this.squareMatrix = squareMatrix;
    }

    public Room[] getRoomList() {
        return roomList;
    }

    public void setRoomList(Room[] roomList) {
        this.roomList = roomList;
    }
}

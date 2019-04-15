package it.polimi.ingsw.board;



public class Map {

    private Square[][] squareMatrix;
    private Room[] roomList;


    //takes the selection and initiates squareMatrix with the squares corresponding to the selected map

    public Map(Square[][] squareMatrix, Room[] roomList) {
        this.squareMatrix = squareMatrix;
        this.roomList = roomList;
    }

    /*
    public static void initiateTerrain(Cell[][] terrain) {

        int i;
        int k;

        for (i = 0; i < 3; i++) {
            for (k = 0; k < 4; k++) {
                terrain[i][k] = new Cell(i, k);
            }
        }
    }
     */

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

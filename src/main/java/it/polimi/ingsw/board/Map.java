package it.polimi.ingsw.board;


import java.util.ArrayList;

public class Map {

    private Square[][] squareMatrix;
    private Room[] roomList;


    //takes the selection and initiates squareMatrix with the squares corresponding to the selected map

    public Map(Square[][] squareMatrix, Room[] roomList) {
        this.squareMatrix = squareMatrix;
        this.roomList = roomList;
    }

    public Room[] getRoomList() {
        return roomList;
    }

    public static void initiateTerrain(Cell[][] terrain) {

        int i = 0;
        int k = 0;

        for (i = 0; i < 3; i++) {
            for (k = 0; k < 4; k++) {
                terrain[i][k] = new Cell(i, k);
            }
        }
    }
}

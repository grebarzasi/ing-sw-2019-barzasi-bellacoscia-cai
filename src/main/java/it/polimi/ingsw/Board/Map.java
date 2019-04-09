package it.polimi.ingsw.Board;


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
}


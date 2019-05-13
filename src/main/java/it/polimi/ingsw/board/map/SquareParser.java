package it.polimi.ingsw.board.map;


/**
 * parses map files
 * @author Yuting Cai
 */

public class SquareParser {

    private int row;
    private int column;

    private Room room;
    private Boolean isRespawn;




    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Boolean getIsRespawn() {
        return isRespawn;
    }

}

package it.polimi.ingsw.model.board.map;


/**
 * parses confinement files
 * @author Yuting Cai
 */

public class SquareConfinementParser {

    private int row;
    private int column;

    private Boolean north;
    private Boolean east;
    private Boolean south;
    private Boolean west;


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

    public Boolean getNorth() {
        return north;
    }

    public Boolean getEast() {
        return east;
    }

    public Boolean getSouth() {
        return south;
    }

    public Boolean getWest() {
        return west;
    }

}

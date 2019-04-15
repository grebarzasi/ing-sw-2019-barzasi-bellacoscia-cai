package it.polimi.ingsw.board;


/**
 * parses confinement files
 */

public class SquareConfinementParser {

    private int row;
    private int column;

    private Boolean north;
    private Boolean east;
    private Boolean South;
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

    public void setNorth(Boolean north) {
        this.north = north;
    }

    public Boolean getEast() {
        return east;
    }

    public void setEast(Boolean east) {
        this.east = east;
    }

    public Boolean getSouth() {
        return South;
    }

    public void setSouth(Boolean south) {
        South = south;
    }

    public Boolean getWest() {
        return west;
    }

    public void setWest(Boolean west) {
        this.west = west;
    }
}

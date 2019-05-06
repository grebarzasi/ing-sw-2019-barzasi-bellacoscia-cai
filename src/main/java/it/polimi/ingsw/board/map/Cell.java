package it.polimi.ingsw.board.map;


/**
 * represents a row;column cell of the matrix
 * @author Yuting Cai
 */


public class Cell {

    private int row;
    private int column;

    public Cell(int row, int tmp) {
        this.row = row;
        this.column = tmp;
    }



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

}
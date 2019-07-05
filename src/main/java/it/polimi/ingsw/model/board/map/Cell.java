package it.polimi.ingsw.model.board.map;


/**
 * represents a row;column cell of the matrix
 * @author Yuting Cai
 */


public class Cell {

    /**
     * row in the matrix
     */
    private int row;
    /**
     * column in the matrix
     */
    private int column;

    /**
     * simple constructor that takes the row and column and creates the cell
     * @param row row in the matrix
     * @param tmp column in the matrix
     */
    Cell(int row, int tmp) {
        this.row = row;
        this.column = tmp;
    }

    /**
     * returns the row
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * returns the column
     * @return the column
     */
    public int getColumn() {
        return column;
    }

}
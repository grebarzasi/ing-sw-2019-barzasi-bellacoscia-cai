package it.polimi.ingsw.Board;

public class Cell {
    private int row;
    private int column;

    public Cell(int row, int tmp) {
        this.row = row;
        this.column = tmp;
    }

    public boolean equals(Cell c){
        if ((this.row == c.row)&&(this.column ==c.column))
            return true;
        return false;
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
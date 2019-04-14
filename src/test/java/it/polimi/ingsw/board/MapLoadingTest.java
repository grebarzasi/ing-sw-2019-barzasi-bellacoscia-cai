package it.polimi.ingsw.board;


import org.junit.Test;

import static it.polimi.ingsw.board.MapLoader.loadMap;
import it.polimi.ingsw.board.MapLoader;
import static org.junit.Assert.*;

public class MapLoadingTest {


    @Test
    public void testLoading(){

        String selection = "large";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        /* I didn't test with prints
        System.out.println(squareMatrix[0][0].getIsRespawn());
        System.out.println(squareMatrix[0][0].getRoom().getColor());
        */

        assertFalse(squareMatrix[0][0].getIsRespawn());
        assertEquals(squareMatrix[0][0].getRoom().getColor(),"red");

    }

    @Test

    /*Test asserts there are no one way doors, if a square has another square as north that square must have the
    first square as south, does not test a method, only verifies that the files have been written correctly.

    Doesn't pass YET because I messed up the .json files :(
     */
    public void testNoOneWayDoors(){

        String selection = "large";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for( row = 0 ; row < 3 ; row++){
            for (column = 0; column <4 ; column ++){

                if(squareMatrix[row][column].getNorth() == squareMatrix[row-1][column]){
                    assertEquals(squareMatrix[row-1][column].getSouth(),squareMatrix[row][column]);
                }

            }
        }


    }

}
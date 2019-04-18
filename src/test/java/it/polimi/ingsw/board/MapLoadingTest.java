package it.polimi.ingsw.board;


import org.junit.Test;

import static it.polimi.ingsw.board.MapLoader.loadMap;
import static org.junit.Assert.*;

public class MapLoadingTest {


    /**
     * Tests that the map is loaded correctly
     */

    @Test
    public void testLoading(){

        String selection = "large";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        assertFalse(squareMatrix[0][0].getIsRespawn());
        assertEquals(squareMatrix[0][0].getRoom().getColor(),"red");

        assertNull(squareMatrix[0][0].getNorth());
        assertEquals(squareMatrix[0][0].getEast(),squareMatrix[0][1]);
        assertEquals(squareMatrix[0][0].getSouth(),squareMatrix[2][0].getNorth());


    }

    /**
     * Test asserts there are no one way doors, if a square has another square as north that square must have the
     *first square as south, does not test a method, only verifies that the files have been written correctly.
     * Done for each map.
     * */

    @Test

    public void testNoOneWayDoorsLarge() {

        String selection = "large";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == squareMatrix[row - 1][column]) {
                    assertEquals(squareMatrix[row - 1][column].getSouth(), squareMatrix[row][column]);
                }

                if (column != 3 && squareMatrix[row][column].getEast() == squareMatrix[row][column + 1]) {
                    assertEquals(squareMatrix[row][column + 1].getWest(), squareMatrix[row][column]);
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == squareMatrix[row + 1][column]) {
                    assertEquals(squareMatrix[row + 1][column].getNorth(), squareMatrix[row][column]);
                }

                if (column != 0 && squareMatrix[row][column].getWest() == squareMatrix[row][column - 1]) {
                    assertEquals(squareMatrix[row][column - 1].getEast(), squareMatrix[row][column]);
                }

            }
        }
    }


    @Test
    public void testNoOneWayDoorsMedium1() {

        String selection = "medium1";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == squareMatrix[row - 1][column]) {
                    assertEquals(squareMatrix[row - 1][column].getSouth(), squareMatrix[row][column]);
                }

                if (column != 3 && squareMatrix[row][column].getEast() == squareMatrix[row][column + 1]) {
                    assertEquals(squareMatrix[row][column + 1].getWest(), squareMatrix[row][column]);
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == squareMatrix[row + 1][column]) {
                    assertEquals(squareMatrix[row + 1][column].getNorth(), squareMatrix[row][column]);
                }

                if (column != 0 && squareMatrix[row][column].getWest() == squareMatrix[row][column - 1]) {
                    assertEquals(squareMatrix[row][column - 1].getEast(), squareMatrix[row][column]);
                }

            }
        }
    }


    @Test
    public void testNoOneWayDoorsMedium2() {

        String selection = "medium2";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == squareMatrix[row - 1][column]) {
                    assertEquals(squareMatrix[row - 1][column].getSouth(), squareMatrix[row][column]);
                }

                if (column != 3 && squareMatrix[row][column].getEast() == squareMatrix[row][column + 1]) {
                    assertEquals(squareMatrix[row][column + 1].getWest(), squareMatrix[row][column]);
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == squareMatrix[row + 1][column]) {
                    assertEquals(squareMatrix[row + 1][column].getNorth(), squareMatrix[row][column]);
                }

                if (column != 0 && squareMatrix[row][column].getWest() == squareMatrix[row][column - 1]) {
                    assertEquals(squareMatrix[row][column - 1].getEast(), squareMatrix[row][column]);
                }

            }
        }
    }


    @Test
    public void testNoOneWayDoorsSmall() {

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == squareMatrix[row - 1][column]) {
                    assertEquals(squareMatrix[row - 1][column].getSouth(), squareMatrix[row][column]);
                }

                if (column != 3 && squareMatrix[row][column].getEast() == squareMatrix[row][column + 1]) {
                    assertEquals(squareMatrix[row][column + 1].getWest(), squareMatrix[row][column]);
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == squareMatrix[row + 1][column]) {
                    assertEquals(squareMatrix[row + 1][column].getNorth(), squareMatrix[row][column]);
                }

                if (column != 0 && squareMatrix[row][column].getWest() == squareMatrix[row][column - 1]) {
                    assertEquals(squareMatrix[row][column - 1].getEast(), squareMatrix[row][column]);
                }

            }
        }
    }

    /**
     * Test asserts there are no one way walls, if a square has null in a direction, the square
     * adjacent to it in that direction should have null in the opposite corresponding direction
     * */

    @Test

    public void testNoOneWayWallsLarge() {

        String selection = "large";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == null) {
                    assertNull(squareMatrix[row - 1][column].getSouth());
                }

                if (column != 3 && squareMatrix[row][column].getEast() == null) {
                    assertNull(squareMatrix[row][column + 1].getWest());
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == null) {
                    assertNull(squareMatrix[row + 1][column].getNorth());
                }

                if (column != 0 && squareMatrix[row][column].getWest() == null) {
                    assertNull(squareMatrix[row][column - 1].getEast());
                }
            }
        }
    }

    @Test

    public void testNoOneWayWallsMedium1() {

        String selection = "medium1";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == null) {
                    assertNull(squareMatrix[row - 1][column].getSouth());
                }

                if (column != 3 && squareMatrix[row][column].getEast() == null) {
                    assertNull(squareMatrix[row][column + 1].getWest());
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == null) {
                    assertNull(squareMatrix[row + 1][column].getNorth());
                }

                if (column != 0 && squareMatrix[row][column].getWest() == null) {
                    assertNull(squareMatrix[row][column - 1].getEast());
                }
            }
        }
    }

    @Test

    public void testNoOneWayWallsMedium2() {

        String selection = "medium2";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == null) {
                    assertNull(squareMatrix[row - 1][column].getSouth());
                }

                if (column != 3 && squareMatrix[row][column].getEast() == null) {
                    assertNull(squareMatrix[row][column + 1].getWest());
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == null) {
                    assertNull(squareMatrix[row + 1][column].getNorth());
                }

                if (column != 0 && squareMatrix[row][column].getWest() == null) {
                    assertNull(squareMatrix[row][column - 1].getEast());
                }
            }
        }
    }

    @Test

    public void testNoOneWayWallsSmall() {

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (row != 0 && squareMatrix[row][column].getNorth() == null) {
                    assertNull(squareMatrix[row - 1][column].getSouth());
                }

                if (column != 3 && squareMatrix[row][column].getEast() == null) {
                    assertNull(squareMatrix[row][column + 1].getWest());
                }

                if (row != 2 && squareMatrix[row][column].getSouth() == null) {
                    assertNull(squareMatrix[row + 1][column].getNorth());
                }

                if (column != 0 && squareMatrix[row][column].getWest() == null) {
                    assertNull(squareMatrix[row][column - 1].getEast());
                }
            }
        }
    }









}
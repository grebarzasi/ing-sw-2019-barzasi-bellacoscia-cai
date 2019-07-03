package it.polimi.ingsw.board;


import it.polimi.ingsw.board.map.*;
import org.junit.jupiter.api.Test;


import static it.polimi.ingsw.board.map.MapLoader.loadTerrain;
import static org.junit.Assert.*;

public class MapLoadingTest {





    /**
     * Tests that the map is loaded correctly
     */

    @Test
    public void testLoading(){

        String selection = "medium1";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        assertEquals(Room.VOID,squareMatrix[2][0].getRoom().getColor());


    }

    /**
     * Test asserts there are no one way doors, if a square has another square as north that square must have the
     *first square as south, does not test a method, only verifies that the files have been written correctly.
     * Done for the large map
     * */

    @Test

    public void testNoOneWayDoorsLarge() {

        String selection = "large";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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
     * Test asserts there are no one way doors, if a square has another square as north that square must have the
     *first square as south, does not test a method, only verifies that the files have been written correctly.
     * Done for the medium map 1
     * */

    @Test
    public void testNoOneWayDoorsMedium1() {

        String selection = "medium1";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

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
     * Test asserts there are no one way doors, if a square has another square as north that square must have the
     *first square as south, does not test a method, only verifies that the files have been written correctly.
     * Done for the medium map 2
     * */

    @Test
    public void testNoOneWayDoorsMedium2() {

        String selection = "medium2";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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
     * Test asserts there are no one way doors, if a square has another square as north that square must have the
     *first square as south, does not test a method, only verifies that the files have been written correctly.
     * Done for the small map
     * */

    @Test
    public void testNoOneWayDoorsSmall() {

        String selection = "small";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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
     * Done for the large map
     * */

    @Test

    public void testNoOneWayWallsLarge() {

        String selection = "large";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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


    /**
     * Test asserts there are no one way walls, if a square has null in a direction, the square
     * adjacent to it in that direction should have null in the opposite corresponding direction
     * Done for the medium map 1
     * */

    @Test

    public void testNoOneWayWallsMedium1() {

        String selection = "medium1";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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

    /**
     * Test asserts there are no one way walls, if a square has null in a direction, the square
     * adjacent to it in that direction should have null in the opposite corresponding direction
     * Done for the medium map 2
     * */

    @Test

    public void testNoOneWayWallsMedium2() {

        String selection = "medium2";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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


    /**
     * Test asserts there are no one way walls, if a square has null in a direction, the square
     * adjacent to it in that direction should have null in the opposite corresponding direction
     * Done for the small map
     * */

    @Test

    public void testNoOneWayWallsSmall() {

        String selection = "small";

        Square[][] squareMatrix = new Square[Map.HEIGHT][Map.WIDTH];

        loadTerrain(selection, squareMatrix);

        int row;
        int column;

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

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
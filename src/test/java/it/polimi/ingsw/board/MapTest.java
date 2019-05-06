package it.polimi.ingsw.board;

import it.polimi.ingsw.board.map.Map;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {


    /**
     * Test the correct initiation of a map
     */

    @Test
    public void initiationTest() {

        String selection = "large";

        Map map = new Map(selection);



        assertEquals(map.getSquareMatrix()[0][0].getRoom().getColor(), "red");

        assertNull(map.getSquareMatrix()[0][0].getNorth());
        assertEquals(map.getSquareMatrix()[0][0].getEast(),map.getSquareMatrix()[0][1]);
        assertEquals(map.getSquareMatrix()[0][0].getSouth(),map.getSquareMatrix()[2][0].getNorth());


    }
}
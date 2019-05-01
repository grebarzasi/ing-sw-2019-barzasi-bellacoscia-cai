package it.polimi.ingsw;

import it.polimi.ingsw.board.Room;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.board.PlayerBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static it.polimi.ingsw.board.MapLoader.loadMap;



public class PlayerTest {



    @Test
    public void constructorTest(){

        Player max = new Player("Max","Luna");

        assertEquals("Luna", max.getPersonalBoard().getOwner().getCharacter());
        assertEquals("Max", max.getPersonalBoard().getOwner().getUsername());

    }


    /**
     *Tests that Ned playing as Huskar can see Rob playing as Pudge
     */

    @Test
    public void testCanSee() {

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player ned = new Player("Edward","Huskar",squareMatrix[1][1]);
        Player rob = new Player("Robert","Pudge",squareMatrix[2][2]);

        assertTrue(ned.canSee(rob));

    }

    /**
     * Tests that Ned playing as Tinker cannot see Rob playing as Wukong
     */

    @Test
    public void testCannotSee() {

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player ned = new Player("Edward","Tinker",squareMatrix[1][1]);
        Player rob = new Player("Robert","Wukong",squareMatrix[0][1]);

        assertFalse(ned.canSee(rob));


    }

    /**
     * Tests that Ned playing as Timbersaw can see the {@link Square} s
     */

    @Test
    public void testCanSeeSquare(){

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player ned = new Player("Edward","Timbersaw",squareMatrix[1][0]);
        Square s = squareMatrix[0][0];

        assertTrue(ned.canSeeSquare(s));

    }

    /**
     * Tests that Ned playing as Drow Ranger cannot see the {@link Square} s
     */

    @Test
    public void testCannotSeeSquare(){

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player ned = new Player("Edward","Drow Ranger",squareMatrix[1][0]);
        Square s = squareMatrix[2][1];

        assertFalse(ned.canSeeSquare(s));

    }

    /**
     * Tests that Ned playing as Tony can see the {@link Room} r
     */

    @Test
    public void testCanSeeRoom(){

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player ned = new Player("Edward","Tony",squareMatrix[1][0]);
        Room r = squareMatrix[0][0].getRoom();

        assertTrue(ned.canSeeRoom(r));

    }

    /**
     * Tests that Ned playing as Io cannot see the {@link Room} r
     */


    @Test
    public void testCannotSeeRoom(){

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player ned = new Player("Edward","Io",squareMatrix[1][0]);
        Room r = squareMatrix[2][1].getRoom();

        assertFalse(ned.canSeeRoom(r));

    }


    /**
     * Tests the distance of a Ronnie playing as Charizard to some squares
     * done for the small Map
     * verifies that an empty square cannot be reached (returns -1 value)
     */

    @Test
    public void testDistanceSmallMap() {


        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player dummy = new Player("Ronnie", "Charizard", squareMatrix[0][0]);

        int distance = dummy.distanceTo(squareMatrix[0][3]);
        int distance2 = dummy.distanceTo(squareMatrix[0][1]);
        int distance3 = dummy.distanceTo(squareMatrix[1][1]);
        int distance4 = dummy.distanceTo(squareMatrix[1][2]);

        assertEquals(distance,-1);
        assertEquals(distance2,1);
        assertEquals(distance3,2);
        assertEquals(distance4,3);

        dummy.setPosition(squareMatrix[1][2]);

        distance = dummy.distanceTo(squareMatrix[2][0]);
        distance2 = dummy.distanceTo(squareMatrix[1][2]);
        distance3 = dummy.distanceTo(squareMatrix[1][1]);
        distance4 = dummy.distanceTo(squareMatrix[2][2]);

        assertEquals(distance,-1);
        assertEquals(distance2,0);
        assertEquals(distance3,1);
        assertEquals(distance4,3);

    }

    /**
     * * Tests the distance of a Reggie playing as Blastoise to some squares
     * done for the large Map
     * only needs to work for up to distance = 4 but also tested for 5 because why not ¯\_(ツ)_/¯
     */


    @Test
    public void testDistanceLargeMap() {


        String selection = "large";

        Square[][] squareMatrix = new Square[3][4];

        loadMap(selection, squareMatrix);

        Player dummy = new Player("Reggie", "Blastoise", squareMatrix[0][0]);

        int distance = dummy.distanceTo(squareMatrix[2][3]);
        int distance2 = dummy.distanceTo(squareMatrix[0][3]);
        int distance3 = dummy.distanceTo(squareMatrix[1][1]);
        int distance4 = dummy.distanceTo(squareMatrix[1][2]);

        assertEquals(distance,5);
        assertEquals(distance2,3);
        assertEquals(distance3,2);
        assertEquals(distance4,3);

        dummy.setPosition(squareMatrix[1][1]);

        distance = dummy.distanceTo(squareMatrix[1][0]);
        distance2 = dummy.distanceTo(squareMatrix[1][2]);
        distance3 = dummy.distanceTo(squareMatrix[1][1]);
        distance4 = dummy.distanceTo(squareMatrix[1][3]);

        assertEquals(distance,3);
        assertEquals(distance2,3);
        assertEquals(distance3,0);
        assertEquals(distance4,4);


    }

}


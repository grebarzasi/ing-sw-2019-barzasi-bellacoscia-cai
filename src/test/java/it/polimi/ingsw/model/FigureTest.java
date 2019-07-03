package it.polimi.ingsw.model;

import it.polimi.ingsw.model.board.map.Room;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.GameModel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Token;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static it.polimi.ingsw.model.board.map.MapLoader.loadTerrain;
import static org.junit.jupiter.api.Assertions.*;

class FigureTest {

    /**
     *Tests that Ned playing as Huskar can see Rob playing as Pudge
     */

    @Test
    public void testCanSee() {

        String selection = "small";

        Square[][] squareMatrix = new Square[3][4];

        loadTerrain(selection, squareMatrix);

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

        loadTerrain(selection, squareMatrix);

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

        loadTerrain(selection, squareMatrix);

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

        loadTerrain(selection, squareMatrix);

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

        loadTerrain(selection, squareMatrix);

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

        loadTerrain(selection, squareMatrix);

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

        loadTerrain(selection, squareMatrix);

        Player dummy = new Player("Ronnie", "Charizard", squareMatrix[0][0]);

        int distance = dummy.distanceTo(squareMatrix[0][3]);
        int distance2 = dummy.distanceTo(squareMatrix[0][1]);
        int distance3 = dummy.distanceTo(squareMatrix[1][1]);
        int distance4 = dummy.distanceTo(squareMatrix[1][2]);

        assertEquals(-1,distance);
        assertEquals(1,distance2);
        assertEquals(2,distance3);
        assertEquals(3,distance4);

        dummy.setPosition(squareMatrix[1][2]);

        distance = dummy.distanceTo(squareMatrix[2][0]);
        distance2 = dummy.distanceTo(squareMatrix[1][2]);
        distance3 = dummy.distanceTo(squareMatrix[1][1]);
        distance4 = dummy.distanceTo(squareMatrix[2][2]);

        assertEquals(-1,distance);
        assertEquals(0,distance2);
        assertEquals(1,distance3);
        assertEquals(3,distance4);

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

        loadTerrain(selection, squareMatrix);

        Player dummy = new Player("Reggie", "Blastoise", squareMatrix[0][0]);

        int distance = dummy.distanceTo(squareMatrix[2][3]);
        int distance2 = dummy.distanceTo(squareMatrix[0][3]);
        int distance3 = dummy.distanceTo(squareMatrix[1][1]);
        int distance4 = dummy.distanceTo(squareMatrix[1][2]);

        assertEquals(5,distance);
        assertEquals(3,distance2);
        assertEquals(2,distance3);
        assertEquals(3,distance4);

        dummy.setPosition(squareMatrix[1][1]);

        distance = dummy.distanceTo(squareMatrix[1][0]);
        distance2 = dummy.distanceTo(squareMatrix[1][2]);
        distance3 = dummy.distanceTo(squareMatrix[1][1]);
        distance4 = dummy.distanceTo(squareMatrix[1][3]);

        assertEquals(3,distance);
        assertEquals(3,distance2);
        assertEquals(0,distance3);
        assertEquals(4,distance4);


    }

    /**
     * Verifies that the points are correctly assigned after death
     */


    @Test
    public void deathTest(){



        Player bertie = new Player ("Albert","bertie");
        Player bill = new Player("William","bill");
        Player vic = new Player("Victoria","vic");
        Player jeff = new Player("Jeff","jeff");
        Player stu = new Player("Stuart", "stu");

        GameModel model = new GameModel();
        bertie.setModel(model);
        model.setFrenzy(false);


        Token a = new Token(bertie);
        Token b = new Token(bill);
        Token v = new Token(vic);
        Token j = new Token(jeff);
        Token s = new Token(stu);

        bertie.getPersonalBoard().addDamage(b);
        bertie.getPersonalBoard().addDamage(b);
        bertie.getPersonalBoard().addDamage(v);
        bertie.getPersonalBoard().addDamage(v);
        bertie.getPersonalBoard().addDamage(v);

        bertie.getPersonalBoard().addDamage(j);
        bertie.getPersonalBoard().addDamage(v);
        bertie.getPersonalBoard().addDamage(j);
        bertie.getPersonalBoard().addDamage(j);
        bertie.getPersonalBoard().addDamage(j);

        bertie.getPersonalBoard().addDamage(s);
        bertie.getPersonalBoard().addDamage(s);

        bertie.die();

        assertEquals(8,vic.getPoints());
        assertEquals(6,jeff.getPoints());
        assertEquals(5,bill.getPoints());
        assertEquals(2,stu.getPoints());

    }

    /**
     * Verifies that the points are correctly assigned after death
     */

    @Test
    public void deathTest2(){


        Player albert = new Player ("Albert","albert");
        Player bill = new Player("Bill","bill");
        Player charlie = new Player("Charlie","charlie");
        Player don = new Player("Don","don");
        Player ed = new Player("Ed", "ed");

        GameModel model = new GameModel();
        albert.setModel(model);
        model.setFrenzy(false);

        Token a = new Token(albert);
        Token b = new Token(bill);
        Token c = new Token(charlie);
        Token d = new Token(don);
        Token e = new Token(ed);

        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(d);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(8,charlie.getPoints());
        assertEquals(6,ed.getPoints());
        assertEquals(4,don.getPoints());
        assertEquals(3,bill.getPoints());

    }

    /**
     * Verifies that the points are correctly assigned after death
     */

    @Test
    public void deathTest3(){


        Player albert = new Player ("Albert","albert");
        Player bill = new Player("Bill","bill");
        Player charlie = new Player("Charlie","charlie");
        Player don = new Player("Don","don");
        Player ed = new Player("Ed", "ed");

        GameModel model = new GameModel();
        albert.setModel(model);
        model.setFrenzy(false);

        Token a = new Token(albert);
        Token b = new Token(bill);
        Token c = new Token(charlie);
        Token d = new Token(don);
        Token e = new Token(ed);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(e);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(9,charlie.getPoints());
        assertEquals(6,don.getPoints());
        assertEquals(4,ed.getPoints());
        assertEquals(0,bill.getPoints());

    }

    /**
     * Verifies that the points are correctly assigned after death
     */

    @Test
    public void deathTest4(){


        Player albert = new Player ("Albert","albert");
        Player bill = new Player("Bill","bill");
        Player charlie = new Player("Charlie","charlie");
        Player don = new Player("Don","don");
        Player ed = new Player("Ed", "ed");

        GameModel model = new GameModel();
        albert.setModel(model);
        model.setFrenzy(false);

        Token a = new Token(albert);
        Token b = new Token(bill);
        Token c = new Token(charlie);
        Token d = new Token(don);
        Token e = new Token(ed);

        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(b);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(9,bill.getPoints());
        assertEquals(6,charlie.getPoints());
        assertEquals(4,don.getPoints());
        assertEquals(2,ed.getPoints());

    }

    /**
     * Verifies that damage are correctly inflicted
     */

    @Test
    void inflictDamage() {

        Player rhaegar = new Player("Rhaegal","Prince");
        Player robert = new Player("Robert","Rebel");

        robert.inflictDamage(1,rhaegar);

        assertEquals(1,rhaegar.getPersonalBoard().getDamage().size());

        rhaegar.inflictDamage(2,robert);
        robert.inflictDamage(20,rhaegar);

        assertEquals(12,rhaegar.getPersonalBoard().getDamage().size());
        assertEquals(2,robert.getPersonalBoard().getDamage().size());

        int i;
        for(i=0;i<12;i++){

            assertEquals(rhaegar.getPersonalBoard().getDamage().get(i).getOwner(),robert);

        }


    }

    /**
     * Asserts that the damage assignment is correct after multiple kills occured
     */

    @Test
    void multipleDeaths() {

        Player albert = new Player ("Albert","albert");
        Player bill = new Player("Bill","bill");
        Player charlie = new Player("Charlie","charlie");
        Player don = new Player("Don","don");
        Player ed = new Player("Ed", "ed");

        GameModel model = new GameModel();
        albert.setModel(model);
        model.setFrenzy(false);

        Token a = new Token(albert);
        Token b = new Token(bill);
        Token c = new Token(charlie);
        Token d = new Token(don);
        Token e = new Token(ed);

        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(d);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(8,charlie.getPoints());
        assertEquals(6,ed.getPoints());
        assertEquals(4,don.getPoints());
        assertEquals(3,bill.getPoints());

        albert.getPersonalBoard().addDamage(b);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(e);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(d);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(14,charlie.getPoints());
        assertEquals(10,ed.getPoints());
        assertEquals(6,don.getPoints());
        assertEquals(5,bill.getPoints());

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(d);
        albert.getPersonalBoard().addDamage(e);

        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);
        albert.getPersonalBoard().addDamage(c);

        albert.die();

        assertEquals(0,albert.getPersonalBoard().getDamage().size());
        assertEquals(0,albert.getPoints());

        assertEquals(19,charlie.getPoints());
        assertEquals(8,don.getPoints());
        assertEquals(11,ed.getPoints());
        assertEquals(5,bill.getPoints());

    }

    /**
     * Verifies the correct interaction between damage and marks
     */

    @Test
    public void damageAndMark(){

        Player albert = new Player ("Albert","albert");
        Player bill = new Player("Bill","bill");
        Player charlie = new Player("Charlie","charlie");
        Player don = new Player("Don","don");
        Player ed = new Player("Ed", "ed");

        GameModel model = new GameModel();
        albert.setModel(model);
        model.setFrenzy(false);

       albert.inflictMark(2,bill);
       assertEquals(2,bill.getPersonalBoard().getMark().size());
       assertEquals(0,bill.getPersonalBoard().getDamage().size());

       albert.inflictDamage(2,bill);

       assertEquals(0,bill.getPersonalBoard().getMark().size());
       assertEquals(4,bill.getPersonalBoard().getDamage().size());

       albert.inflictDamage(1,bill);

       assertEquals(5,bill.getPersonalBoard().getDamage().size());

       albert.inflictMark(1,bill);
       charlie.inflictMark(2,bill);
       ed.inflictMark(2,bill);

       assertEquals(5,bill.getPersonalBoard().getDamage().size());
       assertEquals(5,bill.getPersonalBoard().getMark().size());

       charlie.inflictDamage(1,bill);

       assertEquals(8,bill.getPersonalBoard().getDamage().size());
       assertEquals(3,bill.getPersonalBoard().getMark().size());

    }


    /**
     * Verifies all canSee method returns all Figures that the a given arg can actuall see,
     * and asserts that allFIgure returns all igures in the game
     */
    
    @Test
    public void allCanSeeAndAllFiguresTest(){

        Player underwood = new Player("frank", "president");
        Player russo = new Player("peter", "corpse");
        Player barnes = new Player("zoe", "flat corpse");
        Player stamper = new Player("doug", "chief of staff");
        Player tusk = new Player("raymond", "nuclear guy");
        
        ArrayList<Player> people = new ArrayList<>();


        people.add(underwood);
        people.add(russo);
        people.add(barnes);
        people.add(stamper);
        people.add(tusk);
        
        GameModel washington = new GameModel(people, "large", null);
        
        for(Player p: people){
            p.setModel(washington);
        }

        underwood.setPosition(washington.getBoard().getMap().getSquareMatrix()[1][1]);
        russo.setPosition(washington.getBoard().getMap().getSquareMatrix()[1][1]);
        barnes.setPosition(washington.getBoard().getMap().getSquareMatrix()[0][0]);
        stamper.setPosition(washington.getBoard().getMap().getSquareMatrix()[2][0]);
        tusk.setPosition(washington.getBoard().getMap().getSquareMatrix()[0][2]);

        Set<Figure> underwoodCanSee = underwood.allCanSee();

        assertTrue(underwoodCanSee.contains(russo));
        assertTrue(!underwoodCanSee.contains(barnes));
        assertTrue(underwoodCanSee.contains(stamper));
        assertTrue(underwoodCanSee.contains(tusk));
        assertTrue(underwoodCanSee.contains(underwood));

        Set<Figure> allPeople = underwood.allFigures();

        assertTrue(allPeople.containsAll(people));
        
        
    }




}
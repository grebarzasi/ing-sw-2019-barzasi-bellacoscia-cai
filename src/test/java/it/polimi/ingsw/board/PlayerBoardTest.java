package it.polimi.ingsw.board;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBoardTest {


    /**
     * Test verifies that Mercader correctly deals damage to Trotsky
     * First verifies that Trotsky cannot receive damage after being overkilled
     *
     * Also verifies that Mercader has received Trotsky's vendetta mark
     */

    @Test
    void damage() {


        Player trotsky = new Player("Trotsky","victim",new PlayerBoard (null));
        PlayerBoard trotskyBoard = new PlayerBoard(trotsky);
        trotsky.setPersonalBoard(trotskyBoard);

        Player mercader = new Player("Mercader","assassin",new PlayerBoard (null));
        PlayerBoard mercaderBoard = new PlayerBoard(trotsky);
        mercader.setPersonalBoard(mercaderBoard);

        assertTrue(trotskyBoard.getDamage().isEmpty());

        Token t = new Token(mercader);


        int i;
        for(i = 0 ; i < 4 ; i++){

            trotskyBoard.damage(t);

        }

        assertEquals(trotskyBoard.getDamage().size(),4);

        for(i=4 ; i<11 ; i++){

            trotskyBoard.damage(t);

        }

        assertEquals(trotskyBoard.getDamage().size(),11);

        trotskyBoard.damage(t);

        assertEquals(trotskyBoard.getDamage().size(),12);

        trotskyBoard.damage(t);
        trotskyBoard.damage(t);
        trotskyBoard.damage(t);
        trotskyBoard.damage(t);


        assertEquals(trotskyBoard.getDamage().size(),12);

        assertFalse(mercaderBoard.getMark().isEmpty());
        assertTrue(mercaderBoard.getMark().get(0).getOwner() == trotsky);


    }

    /**
     * Test that damage is reset correctly
     */

    @Test
    void resetDamage() {

        Player trotsky = new Player("Trotsky","victim",new PlayerBoard (null));
        PlayerBoard trotskyBoard = new PlayerBoard(trotsky);
        trotsky.setPersonalBoard(trotskyBoard);

        Player mercader = new Player("Mercader","assassin",new PlayerBoard (null));
        PlayerBoard mercaderBoard = new PlayerBoard(trotsky);
        mercader.setPersonalBoard(mercaderBoard);

        assertTrue(trotskyBoard.getDamage().isEmpty());

        Token t = new Token(mercader);


        int i;
        for(i = 0 ; i < 4 ; i++){

            trotskyBoard.damage(t);

        }

        assertEquals(trotskyBoard.getDamage().size(),4);

        for(i=4 ; i<11 ; i++){

            trotskyBoard.damage(t);

        }

        assertEquals(trotskyBoard.getDamage().size(),11);

        trotskyBoard.damage(t);

        assertEquals(trotskyBoard.getDamage().size(),12);

        trotskyBoard.damage(t);
        trotskyBoard.damage(t);
        trotskyBoard.damage(t);
        trotskyBoard.damage(t);


        assertEquals(trotskyBoard.getDamage().size(),12);

        trotsky.getPersonalBoard().resetDamage();

        assertTrue(trotsky.getPersonalBoard().getDamage().isEmpty());

    }

    /**
     * Tests that Marks are added correctly
     */

    @Test
    void addMark() {

        Player trotsky = new Player("Trotsky","victim",new PlayerBoard (null));
        PlayerBoard trotskyBoard = new PlayerBoard(trotsky);
        trotsky.setPersonalBoard(trotskyBoard);

        Player mercader = new Player("Mercader","victim",new PlayerBoard (null));
        PlayerBoard mercaderBoard = new PlayerBoard(mercader);
        mercader.setPersonalBoard(mercaderBoard);


        Token t = new Token(mercader);
        Token v = new Token(trotsky);
        trotskyBoard.addMark(t);

        assertFalse(trotskyBoard.getMark().isEmpty());
        assertTrue(trotskyBoard.getMark().get(0) == t);


        t.getOwner().getPersonalBoard().addMark(v);
        assertFalse(mercader.getPersonalBoard().getMark().isEmpty());
    }

    /**
     * Test verifies that marks are removed correctly
     */

    @Test
    void removeMark() {

        Player trotsky = new Player("Trotsky","victim",new PlayerBoard (null));
        PlayerBoard trotskyBoard = new PlayerBoard(trotsky);
        trotsky.setPersonalBoard(trotskyBoard);

        Player mercader = new Player("Mercader","victim",new PlayerBoard (null));
        PlayerBoard mercaderBoard = new PlayerBoard(mercader);
        mercader.setPersonalBoard(mercaderBoard);


        Token t = new Token(mercader);
        Token v = new Token(trotsky);
        trotskyBoard.addMark(t);

        assertFalse(trotskyBoard.getMark().isEmpty());
        assertTrue(trotskyBoard.getMark().get(0) == t);


        t.getOwner().getPersonalBoard().addMark(v);
        assertFalse(mercader.getPersonalBoard().getMark().isEmpty());

        trotsky.getPersonalBoard().removeMark(t);
        mercader.getPersonalBoard().removeMark(v);

        assertTrue(mercader.getPersonalBoard().getMark().isEmpty());
        assertTrue(trotsky.getPersonalBoard().getMark().isEmpty());
    }


    @Test
    void addDamage() {


        Player trotsky = new Player("Trotsky","victim",new PlayerBoard (null));
        PlayerBoard trotskyBoard = new PlayerBoard(trotsky);
        trotsky.setPersonalBoard(trotskyBoard);

        Player mercader = new Player("Mercader","assassin",new PlayerBoard (null));
        PlayerBoard mercaderBoard = new PlayerBoard(trotsky);
        mercader.setPersonalBoard(mercaderBoard);

        assertTrue(trotskyBoard.getDamage().isEmpty());

        Token t = new Token(mercader);

        int i;
        for(i=0;i<6;i++) {

            trotskyBoard.addMark(t);

        }

        assertEquals(6,trotskyBoard.getMark().size());

        assertEquals(trotskyBoard.getDamage().size(),0);

        System.out.println("test" + trotskyBoard.getMark().size());
        System.out.println("test" + trotskyBoard.getDamage().size());

        trotskyBoard.addDamage(t);

        System.out.println(trotskyBoard.getDamage().size());

        assertEquals(trotskyBoard.getDamage().size(),7);
        assertEquals(trotskyBoard.getMark().size(),0);

    }


    @Test
    void addSkull() {
    }

    @Test
    void addAmmo() {
    }

    @Test
    void removeAmmo() {
    }
}
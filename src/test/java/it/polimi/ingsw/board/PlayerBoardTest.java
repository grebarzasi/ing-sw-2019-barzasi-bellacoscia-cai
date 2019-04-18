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
        assertSame(mercaderBoard.getMark().get(0).getOwner(), trotsky);


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
        assertSame(trotskyBoard.getMark().get(0), t);


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
        assertSame(trotskyBoard.getMark().get(0), t);


        t.getOwner().getPersonalBoard().addMark(v);
        assertFalse(mercader.getPersonalBoard().getMark().isEmpty());

        trotsky.getPersonalBoard().removeMark(t);
        mercader.getPersonalBoard().removeMark(v);

        assertTrue(mercader.getPersonalBoard().getMark().isEmpty());
        assertTrue(trotsky.getPersonalBoard().getMark().isEmpty());
    }

    /**
     * Verifies that the damage is added correctly considering marks
     */

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

        trotskyBoard.addDamage(t);

        assertEquals(trotskyBoard.getDamage().size(),7);
        assertEquals(trotskyBoard.getMark().size(),0);

        for(i=6;i<20;i++) {

            trotskyBoard.addMark(t);

        }

        trotskyBoard.addDamage(t);

        assertEquals(12,trotskyBoard.getDamage().size());
        assertTrue(trotskyBoard.getMark().isEmpty());


    }

    /**
     * Verifies the correct interaction of marks from 2 different players
     */

    @Test
    void addDamage2Players() {


        Player trotsky = new Player("Trotsky","victim",new PlayerBoard (null));
        PlayerBoard trotskyBoard = new PlayerBoard(trotsky);
        trotsky.setPersonalBoard(trotskyBoard);

        Player mercader = new Player("Mercader","assassin",new PlayerBoard (null));
        PlayerBoard mercaderBoard = new PlayerBoard(trotsky);
        mercader.setPersonalBoard(mercaderBoard);

        Player stalin = new Player("Stalin","contractor",new PlayerBoard (null));
        PlayerBoard stalinBoard = new PlayerBoard(stalin);
        stalin.setPersonalBoard(stalinBoard);

        assertTrue(trotskyBoard.getDamage().isEmpty());

        Token t = new Token(mercader);
        Token c = new Token(stalin);

        int i;


        //mercader gives 6 tokens to trotsky

        for(i=0;i<6;i++) {

            trotskyBoard.addMark(t);

        }

        //stalin gives 3 tokens to trotsky

        for(i=0;i<3;i++) {

            trotskyBoard.addMark(c);

        }

        //trotsky should have 6+3 marks

        assertEquals(9,trotskyBoard.getMark().size());

        //nobody has dealt any damage yet

        assertEquals(trotskyBoard.getDamage().size(),0);

        //stalin deals 1 damage

        trotskyBoard.addDamage(c);

        //only mercader's 6 marks should be left

        assertEquals(trotskyBoard.getMark().size(),6);

        //mercarder deals 1 damage

        trotskyBoard.addDamage(t);

        //total damage dealt should now be 11 and all marks should have been applied

        assertEquals(trotskyBoard.getDamage().size(),11);
        assertEquals(trotskyBoard.getMark().size(),0);

        //mercader applies 20 marks to trotsky because why not

        for(i=6;i<20;i++) {

            trotskyBoard.addMark(t);

        }

        //mercarder applies an ice pick to trotsky's head

        trotskyBoard.addDamage(t);

        //trotsky recieves the ice pick to the head but fights back giving mercarder a mark, but dies in the hospital

        assertEquals(12,trotskyBoard.getDamage().size());
        assertEquals(1,mercaderBoard.getMark().size());

        //all trotsky's marks should be cleared, mercarder wasted his 20 marks

        assertTrue(trotskyBoard.getMark().isEmpty());


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
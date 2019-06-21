package it.polimi.ingsw.controller;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Room;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    /**
     * Tests the test controller
     */

    @Test
    public void constructorTest() {


        Player frank = new Player("Underwood", "President");
        Player doug = new Player("Stamper", "CoS");
        Player seth = new Player("Grayson", "PressS");


        ArrayList<Player> players = new ArrayList<>();

        players.add(frank);
        players.add(doug);
        players.add(seth);

        Controller whiteHouse = new Controller(players);

        for(Player p: players){
            assertEquals(true,p.isDead());
        }

        assertEquals(frank,whiteHouse.getCurrentPlayer());

        whiteHouse.iteratePlayer();

        assertEquals(doug,whiteHouse.getCurrentPlayer());

        whiteHouse.iteratePlayer();

        assertEquals(seth,whiteHouse.getCurrentPlayer());

        whiteHouse.iteratePlayer();

        assertEquals(frank,whiteHouse.getCurrentPlayer());

        frank.setPosition(whiteHouse.getModel().getBoard().getMap().getSquareMatrix()[0][0]);

        ArrayList<Square> frankCanGo = (whiteHouse.canGo(frank,1));

        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[0][0]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[0][1]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[1][0]));
        assertFalse(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[1][1]));

        frankCanGo = (whiteHouse.canGo(frank,2));

        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[0][0]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[0][1]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[1][0]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[2][0]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[0][2]));
        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[1][1]));
        assertFalse(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[2][1]));

        frankCanGo = (whiteHouse.canGo(frank,0));

        assertTrue(frankCanGo.contains(whiteHouse.getBoard().getMap().getSquareMatrix()[0][0]));

        frankCanGo = (whiteHouse.canGo(frank,100));

        int row;
        int column;

        for(row = 0; row < 3; row++) {
            for (column = 0; column < 4; column++) {

                if (!whiteHouse.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor().equals(Room.VOID)) {
                    assertTrue(frankCanGo.contains((whiteHouse.getBoard().getMap().getSquareMatrix()[row][column])));
                }
            }
        }
    }

    /**
     * Tests that the powerups of a different name than selected are correctly filtered
     */

    @Test
    public void powerUpFilterTest(){

        ArrayList<PowerUp> unfiltered = new ArrayList<>();

        unfiltered.add(new PowerUp(null, "Mango"));
        unfiltered.add(new PowerUp(null, "Tango"));
        unfiltered.add(new PowerUp(null, "Tango"));
        unfiltered.add(new PowerUp(null, "Mango"));
        unfiltered.add(new PowerUp(null, "Salve"));
        unfiltered.add(new PowerUp(null, "Salve"));
        unfiltered.add(new PowerUp(null, "Clarity"));
        unfiltered.add(new PowerUp(null, "Tango"));
        unfiltered.add(new PowerUp(null, "Mango"));
        unfiltered.add(new PowerUp(null, "Tango"));
        unfiltered.add(new PowerUp(null, "Clarity"));

        Controller.filterPUs(unfiltered,"Mango");

        assertEquals(3,unfiltered.size());

    }
}
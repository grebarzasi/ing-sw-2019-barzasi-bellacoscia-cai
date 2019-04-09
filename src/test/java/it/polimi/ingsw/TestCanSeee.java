package it.polimi.ingsw;

import it.polimi.ingsw.Board.Cell;
import it.polimi.ingsw.Board.Room;
import it.polimi.ingsw.Board.Square;
import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class TestCanSeee {

    @Test
    public void testCanSee() {
        GameControllerServer testServer = new GameControllerServer(null, null, null, null);

        Cell cell1 = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);

        Room room1 = new Room("red");
        Room room2 = new Room("blue");

        Square square1 = new Square(testServer , cell1 , room1 , false);
        Square square2 = new Square(testServer , cell2 , room2, false);

        square1.setSouth(square2);
        square2.setNorth(square1);

        Player player1 = new Player("1", "A", square1);
        Player player2 = new Player("1", "A", square2);


        assertTrue(player1.canSee(player2));

    }

    @Test
    public void testCannotSee() {
        GameControllerServer testServer = new GameControllerServer(null, null, null, null);

        Cell cell1 = new Cell(0, 0);
        Cell cell2 = new Cell(0, 1);

        Room room1 = new Room("red");
        Room room2 = new Room("blue");

        Square square1 = new Square(testServer , cell1 , room1 , false);
        Square square2 = new Square(testServer , cell2 , room2, false);


        Player player1 = new Player("1", "A", square1);
        Player player2 = new Player("1", "A", square2);


        assertTrue(!(player1.canSee(player2)));

    }

}


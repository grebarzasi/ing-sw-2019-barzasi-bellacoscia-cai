package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Cell;
import it.polimi.ingsw.board.Room;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeleporterTest {

    private Ammo a = new Ammo(1,0,0);
    private Cell pos1 = new Cell(1, 1);
    private Cell pos2 = new Cell(2, 2);
    private String color = "blue";
    private Room room = new Room(color);
    private Square s1 = new Square(pos1, room);
    private Square s2 = new Square(pos2, room);
    private Player p = new Player("username", "character", s1);

    private Teleporter tel = new Teleporter( a ,"Teletrasporto");

    @Test
    void effectTest() {
        /*
        tel.effect(s2, p);
        assertEquals(pos2.getRow(), p.getPosition().getPosition().getRow(), "Teletrasporto non riuscito");
        assertEquals(pos2.getColumn(), p.getPosition().getPosition().getColumn(),"Teletrasporto non riuscito");
        */
    }
}
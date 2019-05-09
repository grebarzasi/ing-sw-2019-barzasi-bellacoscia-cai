package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Room;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.AmmoLot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static it.polimi.ingsw.board.map.MapLoader.loadMap;



public class PlayerTest {



    @Test
    public void constructorTest(){

        Player max = new Player("Max","Luna");

        assertEquals("Luna", max.getPersonalBoard().getOwner().getCharacter());
        assertEquals("Max", ((Player)max.getPersonalBoard().getOwner()).getUsername());

        assertEquals(0,max.getPersonalBoard().getDamage().size());
        assertEquals(0,max.getPoints());

    }

    @Test
    public void ammoPickingTest() {



        int i;

        //test is ran for 100 times since there is random factor;
        for(i=0;i<100;i++) {


            GameControllerServer empire = new GameControllerServer(null, null, null, new Board("small"));
            Board alderaan = empire.getCurrentBoard();
            Player luke = new Player("Luke", "Jedi");
            luke.setControllerServer(empire);
            Square aldera = alderaan.getMap().getSquareMatrix()[0][0];

            assertNotNull(((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop());

            if (((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().hasPowerup()) {
                assertEquals(2, ((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().getContent().getRed() +
                        ((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().getContent().getBlue() +
                        ((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().getContent().getYellow());
            } else {
                assertEquals(3, ((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().getContent().getRed() +
                        ((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().getContent().getBlue() +
                        ((NonSpawnSquare) alderaan.getMap().getSquareMatrix()[0][0]).getDrop().getContent().getYellow());
            }

            luke.setPosition(aldera);
            AmmoLot tmp = ((NonSpawnSquare) aldera).getDrop();

            assertEquals(0, luke.getPersonalBoard().getAmmoInventory().getRed());
            assertEquals(0, luke.getPersonalBoard().getAmmoInventory().getBlue());
            assertEquals(0, luke.getPersonalBoard().getAmmoInventory().getYellow());

            System.out.println(((NonSpawnSquare) aldera).getDrop().hasPowerup() + "\n");
            System.out.println(((NonSpawnSquare) aldera).getDrop().getContent().getRed());
            System.out.println(((NonSpawnSquare) aldera).getDrop().getContent().getBlue());
            System.out.println(((NonSpawnSquare) aldera).getDrop().getContent().getYellow());


            luke.pickAmmo();

            System.out.println(luke.getPersonalBoard().getAmmoInventory().getRed());
            System.out.println(luke.getPersonalBoard().getAmmoInventory().getBlue());
            System.out.println(luke.getPersonalBoard().getAmmoInventory().getYellow());

            if (tmp.hasPowerup()) {
                assertEquals(1, luke.getPowerupList().size());
                assertEquals(2, luke.getPersonalBoard().getAmmoInventory().getRed() + luke.getPersonalBoard().getAmmoInventory().getBlue() + luke.getPersonalBoard().getAmmoInventory().getYellow());
            } else {
                assertTrue(luke.getPowerupList().isEmpty());
                assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getRed() + luke.getPersonalBoard().getAmmoInventory().getBlue() + luke.getPersonalBoard().getAmmoInventory().getYellow());
            }

        }



    }




}


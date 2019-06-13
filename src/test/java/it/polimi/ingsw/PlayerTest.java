package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Deck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;




public class PlayerTest {


    @Test
    public void constructorTest() {

        Player max = new Player("Max", "Luna");

        assertEquals("Luna", max.getPersonalBoard().getOwner().getCharacter());
        assertEquals("Max", ((Player) max.getPersonalBoard().getOwner()).getUsername());

        assertEquals(0, max.getPersonalBoard().getDamage().size());
        assertEquals(0, max.getPoints());

    }

    /**
     * Asserts that Ammo and powerup picking
     * is done correctly
     *
     *
     * STILL MISSING CASE FOR MAXIMUM POWERUP AS FOR SERVER IS NOT
     * READY FOR ASKING CLIENT TO DISCARD CHOSEN CARD
     */

    @Test
    public void ammoPickingTest() {


        int i;

        //test is ran for 100 times since there is random factor;
        for (i = 0; i < 3; i++) {

            Player luke = new Player("Luke", "Jedi");
            ArrayList<Player> empirePlayers = new ArrayList<>();
            empirePlayers.add(luke);
            GameModel empire = new GameModel(empirePlayers, "small", null);
            Board alderaan = empire.getBoard();
            luke.setModel(empire);
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

            assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getRed());
            assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getBlue());
            assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getYellow());

            luke.pickAmmo();

            if (tmp.hasPowerup()) {
                assertEquals(1, luke.getPowerupList().size());
                assertEquals(9, luke.getPersonalBoard().getAmmoInventory().getRed() + luke.getPersonalBoard().getAmmoInventory().getBlue() + luke.getPersonalBoard().getAmmoInventory().getYellow());
            } else {
                assertTrue(luke.getPowerupList().isEmpty());
                assertEquals(9, luke.getPersonalBoard().getAmmoInventory().getRed() + luke.getPersonalBoard().getAmmoInventory().getBlue() + luke.getPersonalBoard().getAmmoInventory().getYellow());
            }

        }
    }


    /**
     * Asserts that even after picking up everything
     * on Alderaan luke is still bound to having
     * a maximum of 3 ammo per color
     */

   @Test
    public void consecutivePicking(){

       Player luke = new Player("Luke", "Jedi");
       ArrayList<Player> empirePlayers = new ArrayList<>();
       empirePlayers.add(luke);
       GameModel empire = new GameModel(empirePlayers, "small", null);
       Board alderaan = empire.getBoard();
       luke.setModel(empire);

        final int height = 3;
        final int width = 4;
        int row;
        int column;

        for(row = 0 ; row < height ; row++){
            for(column = 0; column < width; column ++){
                if(!alderaan.getMap().getSquareMatrix()[row][column].getRoom().getColor().equals("black")) {
                    luke.setPosition(alderaan.getMap().getSquareMatrix()[row][column]);
                    luke.pickAmmo();
                }
            }
        }

        assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getRed());
        assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getBlue());
        assertEquals(3, luke.getPersonalBoard().getAmmoInventory().getYellow());


    }

    @Test
    public void consecutivePickingOnSameSpot() {

        Player luke = new Player("Luke", "Jedi");
        ArrayList<Player> empirePlayers = new ArrayList<>();
        empirePlayers.add(luke);
        GameModel empire = new GameModel(empirePlayers, "small", null);
        Board alderaan = empire.getBoard();
        luke.setModel(empire);

        int i;

        for(i = 0;i<100;i++) {

            luke.setPosition(alderaan.getMap().getSquareMatrix()[0][0]);
            luke.pickAmmo();
            Deck deck=  alderaan.getPowerupDeck();
            System.out.println("Cycle number: " + i);
            System.out.println("Usable: "+ alderaan.getPowerupDeck().getUsable().size());
            System.out.println("Discarded: "+ alderaan.getPowerupDeck().getDiscarded().size());
            alderaan.refillSquares();

        }

    }

}


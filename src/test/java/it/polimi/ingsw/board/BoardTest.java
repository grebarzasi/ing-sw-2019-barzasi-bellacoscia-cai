package it.polimi.ingsw.board;

import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.SpawnSquare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private static final int width = 4;
    private static final int height = 3;

    /**
     * Tests that the Board has been Initiated correctly with all its elements in check
     */

    @Test
    public void constructorTest(){

        Board fieldOfFire = new Board("small");

        int row;
        int column;

        for(row = 0; row < height; row++){
            for(column = 0; column < width ; column ++){


                if(fieldOfFire.getMap().getSquareMatrix()[row][column].isSpawn()){

                    //if a square is a respawn square it should have its armory fully filled at the start of a game

                    assertTrue(((SpawnSquare)fieldOfFire.getMap().getSquareMatrix()[row][column]).getArmory().isFull());

                }else{

                    //if it is not a respawn square it should have an ammo lot drop



                    if(((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop() != null &&  ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().hasPowerup()) {

                        //if it is a drop with power up it has 2 ammunition

                        assertEquals(2, ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getRed() +
                                ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getBlue() +
                                ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getYellow());
                    }else if (((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop() != null){

                        //if not it should have 3 ammunition

                        assertEquals(3, ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getRed() +
                                ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getBlue() +
                                ((NonSpawnSquare) fieldOfFire.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getYellow());

                    }

                    assertFalse(fieldOfFire.getPowerupDeck().getUsable().isEmpty());
                    assertTrue(fieldOfFire.getPowerupDeck().getDiscarded().isEmpty());

                }

            }

        }

    }

}
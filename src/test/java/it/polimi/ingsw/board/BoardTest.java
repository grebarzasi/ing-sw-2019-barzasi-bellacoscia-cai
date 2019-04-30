package it.polimi.ingsw.board;

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

        Board mock = new Board("large");

        int row;
        int column;

        for(row = 0; row < height; row++){
            for(column = 0; column < width ; column ++){


                if(mock.getMap().getSquareMatrix()[row][column].isSpawn()){

                    //if a square is a respawn square it should have its armory fully filled at the start of a game

                    assertTrue(((SpawnSquare)mock.getMap().getSquareMatrix()[row][column]).getArmory().isFull());

                }else{

                    //if it is not a respawn square it should have an ammo lot drop



                    if(((NonSpawnSquare)mock.getMap().getSquareMatrix()[row][column]).getDrop().hasPowerup() == true) {

                        //if it is a drop with power up it has 2 ammunition

                        assertTrue(((NonSpawnSquare) mock.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getRed() +
                                ((NonSpawnSquare) mock.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getBlue() +
                                ((NonSpawnSquare) mock.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getYellow() == 2);
                    }else{

                        //if not it should have 3 ammunition

                        assertTrue(((NonSpawnSquare) mock.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getRed() +
                                ((NonSpawnSquare) mock.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getBlue() +
                                ((NonSpawnSquare) mock.getMap().getSquareMatrix()[row][column]).getDrop().getContent().getYellow() == 3);

                    }

                }

            }

        }

    }

}
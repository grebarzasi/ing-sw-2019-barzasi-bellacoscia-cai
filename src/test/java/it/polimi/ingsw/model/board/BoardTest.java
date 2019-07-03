package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.board.map.Map;
import it.polimi.ingsw.model.board.map.NonSpawnSquare;
import it.polimi.ingsw.model.board.map.SpawnSquare;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {




    /**
     * Tests that the Board has been Initiated correctly with all its elements in check
     */

    @Test
    public void constructorTest(){

        Board fieldOfFire = new Board("small");

        int row;
        int column;

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH ; column ++){


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

    /**
     * A new board is created, asserts that the board has been created correctly,
     * all squares are then emptied and then refilled,
     * asserts that the board has been correctly refilled
     */

    @Test
    public void refillTest(){

        Board fieldOfFire = new Board("large");

        int row;
        int column;

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH ; column ++){


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

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH ; column ++) {

                if(fieldOfFire.getMap().getSquareMatrix()[row][column].isSpawn()){
                    ((SpawnSquare)fieldOfFire.getMap().getSquareMatrix()[row][column]).getArmory().getWeaponList().clear();
                }else{
                    ((NonSpawnSquare)fieldOfFire.getMap().getSquareMatrix()[row][column]).setDrop(null);
                }
            }
        }

        fieldOfFire.refillSquares();

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH ; column ++){


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
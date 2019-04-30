package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.weapon.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private static final int size = 36;
    private static final int width = 4;
    private static final int height = 3;

    @Test
    public void instanceTest(){

        Board mock = new Board("large");

        int row;
        int column;

        for(row = 0; row < height; row++){
            for(column = 0; column < width ; column ++){

                System.out.println(mock.getMap().getSquareMatrix()[row][column].getPosition().getRow() + "," +
                        mock.getMap().getSquareMatrix()[row][column].getPosition().getColumn());

                if(mock.getMap().getSquareMatrix()[row][column] instanceof SpawnSquare){

                    SpawnSquare tmp;
                    tmp = (SpawnSquare)mock.getMap().getSquareMatrix()[row][column];

                    System.out.println(tmp.getArmory().getWeaponList().get(0).getName());
                    System.out.println(tmp.getArmory().getWeaponList().get(1).getName());
                    System.out.println(tmp.getArmory().getWeaponList().get(2).getName());


                }else{

                    NonSpawnSquare tmp;
                    tmp = (NonSpawnSquare)mock.getMap().getSquareMatrix()[row][column];

                    System.out.println(tmp.getDrop().getContent().getRed());
                    System.out.println(tmp.getDrop().getContent().getBlue());
                    System.out.println(tmp.getDrop().getContent().getYellow());


                }

            }
        }

    }

}
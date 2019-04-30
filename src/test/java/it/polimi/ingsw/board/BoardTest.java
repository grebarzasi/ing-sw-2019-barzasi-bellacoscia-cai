package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.weapon.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void instanceTest(){

        Board mock = new Board("large");

        int row;
        int column;

        for(row = 0; row < 3; row++){
            for(column = 0; column < 4 ; column ++){

                System.out.println(mock.getMap().getSquareMatrix()[row][column].getPosition().getRow() + "," +
                        mock.getMap().getSquareMatrix()[row][column].getPosition().getColumn());

                if(mock.getMap().getSquareMatrix()[row][column] instanceof SpawnSquare){

                    SpawnSquare tmp = new SpawnSquare(null,null,null);
                    tmp = (SpawnSquare)mock.getMap().getSquareMatrix()[row][column];

                    System.out.println(tmp.getArmory().get(0).getName());
                    System.out.println(tmp.getArmory().get(1).getName());
                    System.out.println(tmp.getArmory().get(2).getName());


                }else{

                    NonSpawnSquare tmp = new NonSpawnSquare(null,null,null);
                    tmp = (NonSpawnSquare)mock.getMap().getSquareMatrix()[row][column];

                    System.out.println(tmp.getDrop().getContent().getRed());
                    System.out.println(tmp.getDrop().getContent().getBlue());
                    System.out.println(tmp.getDrop().getContent().getYellow());


                }

            }
        }

    }

}
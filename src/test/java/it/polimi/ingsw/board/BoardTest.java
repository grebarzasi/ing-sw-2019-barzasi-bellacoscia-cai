package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void instanceTest(){


        Board mock = new Board(null,null,new Deck(),null,null);

        mock.resetBoard("large");




    }

}
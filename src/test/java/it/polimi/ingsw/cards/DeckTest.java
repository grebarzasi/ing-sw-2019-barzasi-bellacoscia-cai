package it.polimi.ingsw.cards;

import org.junit.jupiter.api.Test;
import it.polimi.ingsw.cards.AmmoLot;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void drawDiscardandRefill(){

        Deck ammoDeck = new Deck();
        loadDeck(ammoDeck);

        ArrayList<Card> drawn = new ArrayList<>();

        int i;
        for(i=0;i<10;i++) {

            drawn.add(ammoDeck.fetch());

            assertEquals(36 - (i+1),ammoDeck.getUsable().size());

        }

        assertEquals(10,drawn.size());
        assertEquals(26,ammoDeck.getUsable().size());
        //assertEquals(10,ammoDeck.getDiscarded().size());

        for(i=10;i<35;i++){

            ammoDeck.fetch();

            assertEquals(36 - (i+1),ammoDeck.getUsable().size());
            //assertEquals((i+1) ,ammoDeck.getDiscarded().size());
        }

        assertEquals(1,ammoDeck.getUsable().size());
        //assertEquals(35,ammoDeck.getDiscarded().size());

        ammoDeck.setDiscarded(drawn);
        ammoDeck.fetch();
        ammoDeck.fetch();


        assertEquals(9,ammoDeck.getUsable().size());
        //  assertEquals(0,ammoDeck.getDiscarded().size());

        for(i=0;i<9;i++) {

            ammoDeck.fetch();

            assertEquals(9 - (i+1),ammoDeck.getUsable().size());
            //assertEquals((i+1) ,ammoDeck.getDiscarded().size());
        }

        assertEquals(0,ammoDeck.getUsable().size());
       // assertEquals(10,ammoDeck.getDiscarded().size());

    }

}
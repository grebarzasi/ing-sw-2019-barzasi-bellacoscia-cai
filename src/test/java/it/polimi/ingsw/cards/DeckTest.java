package it.polimi.ingsw.cards;

import org.junit.jupiter.api.Test;
import it.polimi.ingsw.cards.AmmoLot;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    public void drawDiscardandRefill(){

        Deck ammoDeck = new Deck();
        loadDeck(ammoDeck);

        int i;
        for(i=0;i<10;i++) {

            ammoDeck.fetch();

            assertEquals(36 - (i+1),ammoDeck.getUsable().size());
            assertEquals((i+1) ,ammoDeck.getDiscarded().size());
        }

        assertEquals(26,ammoDeck.getUsable().size());
        assertEquals(10,ammoDeck.getDiscarded().size());

        for(i=10;i<35;i++){

            ammoDeck.fetch();

            assertEquals(36 - (i+1),ammoDeck.getUsable().size());
            assertEquals((i+1) ,ammoDeck.getDiscarded().size());
        }

        assertEquals(1,ammoDeck.getUsable().size());
        assertEquals(35,ammoDeck.getDiscarded().size());

        ammoDeck.fetch();

        assertEquals(36,ammoDeck.getUsable().size());
        assertEquals(0,ammoDeck.getDiscarded().size());

        for(i=0;i<10;i++) {

            ammoDeck.fetch();

            assertEquals(36 - (i+1),ammoDeck.getUsable().size());
            assertEquals((i+1) ,ammoDeck.getDiscarded().size());
        }

        assertEquals(26,ammoDeck.getUsable().size());
        assertEquals(10,ammoDeck.getDiscarded().size());

    }

}
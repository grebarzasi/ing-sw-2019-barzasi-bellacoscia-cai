package it.polimi.ingsw.cards;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {



    @Test
    void reset() {

        Deck ammoDeck = new Deck();

        loadDeck(ammoDeck);

        assertEquals(36,ammoDeck.getUsable().size());

        for(int i=0;i<36;i++){
            ammoDeck.discard();
        }

        assertTrue(ammoDeck.getUsable().isEmpty());
        assertEquals(36,ammoDeck.getDiscarded().size());

        ammoDeck.reset();

        assertTrue(ammoDeck.getDiscarded().isEmpty());
        assertEquals(36,ammoDeck.getUsable().size());



    }
}
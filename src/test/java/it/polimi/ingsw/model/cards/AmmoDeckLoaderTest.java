package it.polimi.ingsw.model.cards;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.model.cards.AmmoDeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Asserts that a fully loaded deck has 36 cards
 */

public class AmmoDeckLoaderTest {

    @Test
    public void loadingTest(){

        Deck ammoDeck = new Deck();

        loadDeck(ammoDeck);

        assertEquals(36,ammoDeck.getUsable().size());
    }


}
package it.polimi.ingsw.cards;

import it.polimi.ingsw.Player;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;
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
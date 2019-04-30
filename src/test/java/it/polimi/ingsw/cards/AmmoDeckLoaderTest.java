package it.polimi.ingsw.cards;

import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;


public class AmmoDeckLoaderTest {

    @Test
    public void loadingTest(){

        Deck ammoDeck = new Deck();

        loadDeck(ammoDeck);

        assertEquals(36,ammoDeck.getUsable().size());
    }


}
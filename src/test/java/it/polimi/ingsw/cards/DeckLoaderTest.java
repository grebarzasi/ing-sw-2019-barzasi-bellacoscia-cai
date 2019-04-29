package it.polimi.ingsw.cards;

import it.polimi.ingsw.cards.DeckLoader;
import org.junit.jupiter.api.Test;

import static it.polimi.ingsw.cards.DeckLoader.loadDeck;
import static org.junit.jupiter.api.Assertions.*;


public class DeckLoaderTest {

    @Test
    public void loadingTest(){

        Deck ammoDeck = new Deck();

        loadDeck(ammoDeck);

        assertEquals(36,ammoDeck.getUsable().size());
    }


}
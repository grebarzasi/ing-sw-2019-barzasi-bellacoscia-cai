package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.cards.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckPowerUpBuilderTest {

    DeckPowerUpBuilder usablePU = new DeckPowerUpBuilder();

    Deck usablePUDeck = new Deck(null);


    @Test
    void powerUpBuilderTest() {
        usablePUDeck = usablePU.PowerUpBuilder();
        assertNotNull(usablePUDeck);
        assertFalse(usablePUDeck.getUsable().isEmpty());
    }


}
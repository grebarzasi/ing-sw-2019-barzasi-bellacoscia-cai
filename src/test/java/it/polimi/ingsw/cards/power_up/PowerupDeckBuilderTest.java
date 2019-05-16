package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerupDeckBuilderTest {

    PowerupDeckBuilder usablePU = new PowerupDeckBuilder();

    Deck usablePUDeck = new Deck(null);


    @Test
    void powerUpBuilderTest() {
        usablePUDeck = usablePU.PowerUpBuilder();
        assertNotNull(usablePUDeck);
        assertFalse(usablePUDeck.getUsable().isEmpty());

        for(Card c : usablePUDeck.getUsable()){
            PowerUp p = (PowerUp) c;
            assertNotNull(p.getName());
        }
    }

}
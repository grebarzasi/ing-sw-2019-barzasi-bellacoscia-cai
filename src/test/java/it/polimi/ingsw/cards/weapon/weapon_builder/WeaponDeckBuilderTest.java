package it.polimi.ingsw.cards.weapon.weapon_builder;

import it.polimi.ingsw.cards.Card;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;
import it.polimi.ingsw.cards.weapon.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Gregorio Barzasi
 */

class WeaponDeckBuilderTest {

    @Test
    void buildDeck() {
        WeaponDeck wDeck = WeaponDeckBuilder.buildDeck();
        assertEquals(20,wDeck.getUsable().size());
        for(Card c : wDeck.getUsable()) {
            Weapon weaponCheck = (Weapon)c;
            assertNull(c.getOwner());
        }
        Weapon w ;
        while(!wDeck.getUsable().isEmpty()){
           w =  (Weapon) wDeck.fetch();
        }
        assertNull(wDeck.fetch());
    }
}
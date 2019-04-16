package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.*;
/**
 *Used by {@link Effect} class to represent the sub-level.
 *combinations of sub-effects leads to a complete effect representation
 *
 * @author Gregorio Barzasi
 */
public interface SubEffect {

   Set<Player> applyEffect(Weapon w, Set<Player> target);

}

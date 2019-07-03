package it.polimi.ingsw.model.cards.weapon;

import it.polimi.ingsw.model.Figure;

import java.util.*;
/**
 *Used by {@link Effect} class to represent the sub-level.
 *combinations of sub-effects leads to a complete effect representation
 *
 * @author Gregorio Barzasi
 */
public interface SubEffect {

   public Set<Figure> applyEffect(Weapon w, Set<Figure> target);
   public void resetSubEffect();


}

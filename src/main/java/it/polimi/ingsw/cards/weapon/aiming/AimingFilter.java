package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to represent the aiming Filters.
 *combinations of Filters leads to a correct target selection
 *
 * @author Gregorio Barzasi
 */
public interface AimingFilter {
    /**
     *
     * @param w weapon owner of the effect
     * @param p set of player previously filtered by other filters ( if first equals to allPlayers in game)
     * @return
     */
    Set<Figure> filter(Weapon w, Set<Figure> p);

}

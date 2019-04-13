package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Player;

import java.util.ArrayList;

/**
 *Used by {@link TargetAcquisition} class to represent the aiming Filters.
 *combinations of Filters leads to a correct target selection
 *
 * @author Gregorio Barzasi
 */
public interface AimingFilter {

    public abstract ArrayList<Player> filter();

}

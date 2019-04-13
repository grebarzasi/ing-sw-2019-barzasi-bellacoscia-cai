package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.AimingFilter;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;

import java.util.ArrayList;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones outside the range
 * between "minDistance" and "maxDistance"
 *
 * @author Gregorio Barzasi
 */
public class AimRange implements AimingFilter {

    private Integer minDistance;
    private Integer maxDistance;

    public AimRange(Integer minDistance, Integer maxDistance) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    public ArrayList<Player> filter() {
        return null;

    }
}
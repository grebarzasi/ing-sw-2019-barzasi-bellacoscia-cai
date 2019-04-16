package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.AimingFilter;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones outside the range
 * between "minDistance" and "maxDistance"
 *
 * @author Gregorio Barzasi
 */
public class AimRange implements AimingFilter {



    private Integer minDistance;
    private Integer maxDistance;

    public Integer getMinDistance() {
        return minDistance;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }
    public AimRange(Integer minDistance, Integer maxDistance) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    public Set<Player> filter(Weapon w, Set<Player> p) {
        return p;

    }
}
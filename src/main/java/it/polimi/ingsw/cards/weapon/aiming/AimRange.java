package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones outside the range
 * between "minDistance" ( included) and "maxDistance"(excluded)
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
        if(maxDistance==0)
            this.maxDistance =1000;
        else
            this.maxDistance = maxDistance;
    }

    public Set<Player> filter(Weapon w, Set<Player> p) {
        for(Player i : p){
           int dist = w.getOwner().distanceTo(i.getPosition());
            if (!((minDistance<=dist)&&(maxDistance>dist)))
                p.remove(i);
        }
        return p;
    }
}
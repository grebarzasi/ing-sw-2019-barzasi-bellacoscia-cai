package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.model.cards.weapon.Weapon;

import java.util.HashSet;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones outside the range
 * between "minDistance" ( included) and "maxDistance"(excluded)
 *
 * @author Gregorio Barzasi
 */
public class AimRange implements AimingFilter {


    /**
     * minimum distance from square allowed
     *
     */
    private Integer minDistance;
    /**
     * maximum distance from square allowed
     *
     */
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

    /**
     * aims all player within a certain range from a chosen target
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(p==null)
            return null;
        Set<Figure> temp = new HashSet<>(p);
        for(Figure i : p){
           int dist = w.getOwner().distanceTo(i.getPosition());
            if (!((minDistance<=dist)&&(maxDistance>dist)))
                temp.remove(i);
        }
        return temp;
    }

}
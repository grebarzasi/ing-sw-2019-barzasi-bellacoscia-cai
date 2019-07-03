package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.model.cards.weapon.Weapon;

import java.util.HashSet;
import java.util.Set;


/**
 *Used by {@link TargetAcquisition} class to filter target to only ones layng on selected square or ones in a given range
 * starting from the selected square.
 * Need an input from the player to work.
 *
 *
 * @author Gregorio Barzasi
 */


public class AimSquare implements AimingFilter{
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

    /**
     * indicates that you can target your own square
     *
     */
    private boolean mine;
    /**
     * Temporary set of hittable players
     */
    private Set<Figure> targetTemp;

    /**
     * message shown to players during square choice
     */
    private String msg;



    public Integer getMinDistance() {
        return minDistance;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }




    public AimSquare(Boolean mine,String msg,Integer minDistance, Integer maxDistance){
        this.minDistance = minDistance;
        if(maxDistance==0)
            this.maxDistance =1000;
        else
            this.maxDistance = maxDistance;

        this.mine=mine;
        this.msg=msg;
        this.targetTemp=new HashSet<>();

    }

    /**
     * aims all player within a certain range from a chosen square
     * if controller already set the preference it goes on to the next filter, if not it pauses itself saving the
     * weapon state and return to controller. once a player set the preferences the state is restored and the effect can
     * continue.
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */

    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(w.getSquareTemp()==null){
            targetTemp.clear();
            targetTemp.addAll(p);
            w.setSquareTemp(this);
            return null;
        }
        w.setSquareTemp(null);
        p.clear();
        p.addAll(targetTemp);
        return p;
    }



    public void resetFilter() {
        targetTemp.clear();
    }

    public void setMinDistance(Integer minDistance) {
        this.minDistance = minDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public Set<Figure> getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Set<Figure> targetTemp) {
        this.targetTemp = targetTemp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
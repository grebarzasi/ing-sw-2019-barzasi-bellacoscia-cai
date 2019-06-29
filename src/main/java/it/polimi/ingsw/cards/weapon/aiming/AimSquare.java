package it.polimi.ingsw.cards.weapon.aiming;

import com.sun.org.apache.xpath.internal.operations.Bool;
import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */


public class AimSquare implements AimingFilter{

    private Integer minDistance;
    private Integer maxDistance;
    private boolean mine;
    private Set<Figure> targetTemp;
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

    //does the exclusion between the set of available target and the one from source
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
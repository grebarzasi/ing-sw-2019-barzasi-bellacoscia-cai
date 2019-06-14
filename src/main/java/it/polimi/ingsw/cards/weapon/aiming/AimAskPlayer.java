package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.HashSet;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target to only ones selected by the player.
 * Need an input from the player to work.
 *uses also two constraint to limit the possible options.
 *
 * @author Gregorio Barzasi
 */
public class AimAskPlayer implements AimingFilter {

    private Integer numMax;
    private boolean fromDiffSquare;
    private String msg;
    private Set<Figure> targetTemp;

    public Integer getNumMax() {
        return numMax;
    }

    public String getMsg(){
        return this.msg;
    }

    public boolean isFromDiffSquare() {
        return fromDiffSquare;
    }

    public AimAskPlayer(String msg,Integer numMax, boolean fromDiffSquare) {
        this.numMax = numMax;
        this.fromDiffSquare = fromDiffSquare;
        this.msg=msg;
        this.targetTemp=new HashSet<>();
    }


    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(w.getAskTemp()==null){
          targetTemp=p;
          w.setAskTemp(this);
          return null;
       }else{

        }
            return targetTemp;
    }


    public void resetFilter() {
        targetTemp.clear();
    }

    public void setNumMax(Integer numMax) {
        this.numMax = numMax;
    }

    public void setFromDiffSquare(boolean fromDiffSquare) {
        this.fromDiffSquare = fromDiffSquare;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Set<Figure> getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Set<Figure> targetTemp) {
        this.targetTemp = targetTemp;
    }
}
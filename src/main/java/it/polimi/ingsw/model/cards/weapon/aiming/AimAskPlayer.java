package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.model.cards.weapon.Weapon;

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

    /**
     * Max num of player you can hit
     */
    private Integer numMax;
    /**
     * indicates that players can only belong to different square
     */
    private boolean fromDiffSquare;
    /**
     * the message shown to players
     */
    private String msg;
    /**
     * Temporary set of targettable players
     */
    private Set<Figure> targetTemp;

    /**
     * Creates the instance of {@link AimAskPlayer} and initializes target temp to a new hashset
     */
    public AimAskPlayer(String msg,Integer numMax, boolean fromDiffSquare) {
        this.numMax = numMax;
        this.fromDiffSquare = fromDiffSquare;
        this.msg=msg;
        this.targetTemp=new HashSet<>();
    }

    /**
     * if controller already set the preference it goes on to the next filter, if not it pauses itself saving the
     * weapon state and return to controller. once a player set the preferences the state is restored and the effect can
     * continue.
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */

    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(w.getAskTemp()==null){
            targetTemp.clear();
          targetTemp.addAll(p);
          w.setAskTemp(this);
          //returning null is the expected behaviour. it means that there's not enough info to complete execution
          return null;
       }
        w.setAskTemp(null);
        p.clear();
        p.addAll(targetTemp);
        return p;
    }

    public boolean isFromDiffSquare() {
        return fromDiffSquare;
    }

    public Integer getNumMax() {
        return numMax;
    }

    public String getMsg(){
        return this.msg;
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
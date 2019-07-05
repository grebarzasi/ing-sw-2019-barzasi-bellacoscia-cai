package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.model.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */


public class AimDifferent extends AimComparator implements AimingFilter{


    public AimDifferent(ArrayList<String> sourceList){
        super(sourceList);

    }

    /**
     * does the exclusion between the set of available target and the one from source
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        //returning null is the expected behaviour. it means that there's not enough info to complete execution
        if(p==null)
            return null;
        Figure target = (Figure)p.toArray()[0];
        p.removeAll(getPlayersFromSource(w,target));
        return p;
    }

}
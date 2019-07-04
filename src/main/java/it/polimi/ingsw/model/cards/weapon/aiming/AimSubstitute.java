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


public class AimSubstitute extends AimComparator implements AimingFilter{


    public AimSubstitute(ArrayList<String> sourceList){
        super(sourceList);

    }

    /**
     * does the substitution between the set of available target and the one from source
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(p==null)
            return null;
        if(p.isEmpty()){
            return p;
//            p.add(w.getOwner());
        }
        Figure target = (Figure)p.toArray()[0];
        p=getPlayersFromSource(w, target);
        return p;
    }

}
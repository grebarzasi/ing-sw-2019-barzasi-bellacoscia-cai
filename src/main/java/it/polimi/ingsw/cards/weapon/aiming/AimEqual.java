package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target including only ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */
public class AimEqual extends AimComparator implements AimingFilter {

    public AimEqual(ArrayList<String> sourceList){
        super(sourceList);
    }

    //does the intersection between the set of available target and the one from source
    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(p==null)
            return null;
        Figure target = (Figure)p.toArray()[0];
        p.retainAll(getPlayersFromSource(w,target));
        return p;
    }

    public void resetFilter() {
    }
}
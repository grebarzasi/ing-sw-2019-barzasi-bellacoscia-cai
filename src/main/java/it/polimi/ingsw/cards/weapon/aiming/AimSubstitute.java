package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

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

    //does the exclusion between the set of available target and the one from source
    public Set<Player> filter(Weapon w, Set<Player> p) {
        p=getPlayersFromSource(w);
        return p;
    }


}
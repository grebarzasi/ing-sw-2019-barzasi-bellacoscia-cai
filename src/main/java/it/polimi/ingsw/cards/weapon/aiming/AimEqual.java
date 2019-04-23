package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
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
    public Set<Player> filter(Weapon w, Set<Player> p) {
        Player target = (Player)p;
        p.retainAll(getPlayersFromSource(w,target));
        return p;
    }
}
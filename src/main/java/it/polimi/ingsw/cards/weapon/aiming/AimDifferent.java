package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import java.util.ArrayList;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */


public class AimDifferent extends TargetAcquisition {
    private ArrayList<String> sourceList;
    private ArrayList<ArrayList<Player>> returnList;

    public void filter() {

    }
}
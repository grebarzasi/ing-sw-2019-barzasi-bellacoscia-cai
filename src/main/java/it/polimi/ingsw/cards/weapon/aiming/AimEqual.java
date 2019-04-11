package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;

import java.util.ArrayList;
/**
 *Used by {@link TargetAcquisition} class to filter target including only ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */
public class AimEqual extends TargetAcquisition {
    private ArrayList<ArrayList<Player>> sourceList;

    public void filter() {

    }
}
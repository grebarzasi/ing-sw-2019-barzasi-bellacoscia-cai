package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;

/**
 *Used by {@link TargetAcquisition} class to filter target only to "Visible" or "Not Visible" ones
 * from the perspective of the player saved in the field "origin" ( default value being the owner of the weapon)
 *
 * @author Gregorio Barzasi
 */

public class AimVisible extends TargetAcquisition {

    private boolean visible;
    private Player origin;

    public void filter() {

    }
}

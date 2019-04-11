package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.cards.weapon.TargetAcquisition;
/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones outside the range
 * between "minDistance" and "maxDistance"
 *
 * @author Gregorio Barzasi
 */
public class AimRange extends TargetAcquisition {

    private Integer minDistance;
    private Integer maxDistance;

    public void filter() {

    }
}
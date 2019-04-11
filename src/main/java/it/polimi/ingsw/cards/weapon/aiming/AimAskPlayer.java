package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.cards.weapon.TargetAcquisition;
/**
 *Used by {@link TargetAcquisition} class to filter target to only ones selected by the player.
 * Need an input from the player to work.
 *uses also two constraint to limit the possible options.
 *
 * @author Gregorio Barzasi
 */
public class AimAskPlayer extends TargetAcquisition {

    private Integer numMax;
    private boolean fromDiffSquare;
    private boolean fromDiffRoom;

    public void filter() {

    }
}
package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target to only ones laying on the cardinal direction selected.
 * Need an input from the player to work
 *
 * @author Gregorio Barzasi
 */
public class AimDirection implements AimingFilter {

    private boolean wallBang ;

    public AimDirection(boolean wallBang){
        this.wallBang=wallBang;
    }
    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        return p;

    }
}
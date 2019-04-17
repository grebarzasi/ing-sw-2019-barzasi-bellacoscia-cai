package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.AimingFilter;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target only to "Visible" or "Not Visible" ones
 * from the perspective of the player saved in the field "origin" ( default value being the owner of the weapon)
 *
 * @author Gregorio Barzasi
 */

public class AimVisible implements AimingFilter {

    private boolean visible;
    private String origin;

    public AimVisible(boolean visible,String origin){
       this.origin=origin;
       this.visible=visible;
    }

    public AimVisible(boolean visible){
        this.origin=null;
        this.visible=visible;
    }
    public boolean isVisible() {
        return visible;
    }

    public String getOrigin() {
        return origin;
    }


    public Set<Player> filter(Weapon w, Set<Player> p) {
        if(visible) {
            if (origin.isEmpty())
                p.retainAll(w.getOwner().allCanSee());
            else if (origin.equals("last"))
                p.retainAll(w.getLastHit().allCanSee());
        } else {
            if (origin.isEmpty())
                p.removeAll(w.getOwner().allCanSee());
            else if (origin.equals("last"))
                p.removeAll(w.getLastHit().allCanSee());
        }
        return p;
    }
}

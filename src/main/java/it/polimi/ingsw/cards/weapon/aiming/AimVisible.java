package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
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
    private String origin="";

    public AimVisible(boolean visible,String origin){
       this.origin=origin;
       this.visible=visible;
    }

    public AimVisible(boolean visible){
        this.visible=visible;
    }
    public boolean isVisible() {
        return visible;
    }

    public String getOrigin() {
        return origin;
    }


    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        Figure x=w.getOwner();
        if(p==null)
            return null;

        switch (origin){
                case "last":
                    x=w.getLastHit();
                    break;
                case "basic":
                    x=(Figure)w.getBasicEffect().getTargetHitSet().toArray()[0];
                    break;
                case "addOne":
                    x=(Figure)w.getAddOneEffect().getTargetHitSet().toArray()[0];
                    break;
                case "addTwo":
                    x=(Figure)w.getAddTwoEffect().getTargetHitSet().toArray()[0];
                    break;
                case "alternative":
                    x=(Figure)w.getAddTwoEffect().getTargetHitSet().toArray()[0];
                    break;
            }
        Set<Figure> all = x.allCanSee();

        if(visible) {
            p.retainAll(all);
        } else {
            p.removeAll(all);
        }
        return p;
    }
    public void resetFilter() {
    }
}

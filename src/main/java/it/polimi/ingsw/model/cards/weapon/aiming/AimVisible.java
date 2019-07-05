package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.model.cards.weapon.Weapon;

import java.util.HashSet;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target only to "Visible" or "Not Visible" ones
 * from the perspective of the player saved in the field "origin" ( default value being the owner of the weapon)
 *
 * @author Gregorio Barzasi
 */

public class AimVisible implements AimingFilter {

    /**
     * visible  property indicates that filter have to return only visible or not visible players
     *
     */
    private boolean visible;
    /**
     * is the origin of the "search" of visible / not visible players
     */
    private String origin="";

    public AimVisible(boolean visible,String origin){
       this.origin=origin;
       this.visible=visible;
    }

    public AimVisible(boolean visible){
        this.visible=visible;
    }
    public String getOrigin() {
        return origin;
    }

    /**
     * @return all visible or not visible players based on a origin. the origin can be the last player it or the players
     * hit in other effects
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */
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
            //remove not spawned players
            Set<Figure> temp = new HashSet<>(all);
            for(Figure f : temp) {
                if (f.isDead())
                    all.remove(f);
            }
            p.removeAll(all);
        }
        return p;
    }

    public boolean isVisible() {
        return visible;
    }
}

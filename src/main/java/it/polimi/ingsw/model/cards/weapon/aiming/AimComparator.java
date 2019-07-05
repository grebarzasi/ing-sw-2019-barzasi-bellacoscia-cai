package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 *Used by {@link AimDifferent}{@link AimEqual} and {@link AimSubstitute}  classes
 * to build a set of target based on a source list.
 * after that classes uses the built list in different ways.
 *
 * @author Gregorio Barzasi
 */

public class AimComparator {
    /**
     * saves the source of the search
     */
    private ArrayList<String> sourceList;

    public ArrayList<String> getSourceList() {
        return sourceList;
    }

    public AimComparator(ArrayList<String> s){
        this.sourceList=s;
    }

    /**
     * @return all player corresponding to source list parameters
     * @param w is the weapon actually in use
     * @param target is the set of hittable players at that moment
     */
    public Set<Figure> getPlayersFromSource(Weapon w, Figure target) {
        //returning null is the expected behaviour. it means that there's not enough info to complete execution
        if(target==null)
            return null;
        Set<Figure> pSet = new HashSet<>();
        for (String s : sourceList) {
            switch (s) {
                case "all":
                    pSet.addAll(target.allFigures());
                    break;
                case "prevSelected":
                    pSet.addAll(w.getPrevSelected());
                    break;
                case "basic":
                    pSet.addAll(w.getBasicEffect().getTargetHitSet());
                    break;
                case "addOne":
                    pSet.addAll(w.getAddOneEffect().getTargetHitSet());
                    break;
                case "addTwo":
                    pSet.addAll(w.getAddTwoEffect().getTargetHitSet());
                    break;
                case "alternative":
                    pSet.addAll(w.getAlternativeEffect().getTargetHitSet());
                    break;
                case "extramove":
                    pSet.addAll(w.getExtraMove().getTargetHitSet());
                    break;
                case "last":
                    pSet.add(w.getLastHit());
                    break;
                case "room":
                    pSet.addAll(target.getPosition().getRoom().playersInRoom(target.allFigures()));
                    break;
                case "square":
                    pSet.addAll(target.getPosition().playersInSquare(target.allFigures()));
                    break;
                case "oldSquare":
                    pSet.addAll(target.getOldPosition().playersInSquare(target.allFigures()));
                    break;
                case "myRoom":
                    pSet.addAll(w.getOwner().getPosition().getRoom().playersInRoom(target.allFigures()));
                    break;
                case "mySquare":
                    pSet.addAll(w.getOwner().getPosition().playersInSquare(target.allFigures()));
                    break;
                case "me":
                    pSet.add(w.getOwner());
                    break;

            }
        }
        return pSet;
    }


}

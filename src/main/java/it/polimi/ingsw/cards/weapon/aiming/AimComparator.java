package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Gregorio Barzasi
 */

public class AimComparator {
    private ArrayList<String> sourceList;

    public ArrayList<String> getSourceList() {
        return sourceList;
    }

    public AimComparator(ArrayList<String> s){
        this.sourceList=s;
    }


    public Set<Player> getPlayersFromSource(Weapon w) {
        Set<Player> pSet = new HashSet<>();
        for (String s : sourceList) {
            switch (s) {
                case "base":
                    pSet.addAll(w.getBasicEffect().getTargetHitSet());
                    break;
                case "addOne":
                    pSet.addAll(w.getAddOneEffect().getTargetHitSet());
                    break;
                case "addTwo":
                    pSet.addAll(w.getAddTwoEffect().getTargetHitSet());
                    break;
                case "alternative":
                    pSet.addAll(w.getAddTwoEffect().getTargetHitSet());
                    break;
                case "extramove":
                    pSet.addAll(w.getAddTwoEffect().getTargetHitSet());
                    break;
                case "last":
                    pSet.add(w.getLastHit());
                    break;
                case "room":
                   //implement
                    break;
                case "square":
                    //implement
                    break;
                case "oldSquare":
                   // pSet.add(w.getOwner());
                    break;
                case "myRoom":
                    //pSet.add(w.getOwner().getPosition().getRoom().getPlayersInRoom);
                    break;
                case "mySquare":
                   // pSet.add(w.getOwner().getPosition().playersInSquare());
                    break;
                case "me":
                    pSet.add(w.getOwner());
                    break;


            }
        }
        return pSet;
    }


}

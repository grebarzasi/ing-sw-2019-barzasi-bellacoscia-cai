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


    public Set<Player> getPlayersFromSource(Weapon w,Player target) {
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
                    pSet.addAll(target.getPosition().getRoom().playersInRoom(target.allPlayers()));
                    break;
                case "square":
                    pSet.addAll(target.getPosition().playersInSquare(target.allPlayers()));
                    break;
                case "oldSquare":
                   // Implement here
                    break;
                case "myRoom":
                    pSet.addAll(w.getOwner().getPosition().getRoom().playersInRoom(target.allPlayers()));                    break;
                case "mySquare":
                    pSet.addAll(w.getOwner().getPosition().playersInSquare(target.allPlayers()));
                    break;
                case "me":
                    pSet.add(w.getOwner());
                    break;


            }
        }
        return pSet;
    }


}

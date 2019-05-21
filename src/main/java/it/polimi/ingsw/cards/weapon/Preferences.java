package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.EMPTY_SET;

public class Preferences {
    private Set<Figure> target;
    private Square position;
    private String direction;
    private Figure oneTarget;
    private Ammo ammo;

    public Ammo getAmmo() {
        return ammo;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }

    public Figure getOneTarget() {
        return oneTarget;
    }

    public void setOneTarget(Figure oneTarget) {
        this.oneTarget = oneTarget;
    }

    public  Set<Figure> getTarget() {
        return target;
    }

    public  Set<Figure> getTargetSet(Set<Figure> set,String msg, int max, boolean diffSquare) {
        target.retainAll(set);
        if(!target.isEmpty() && target.size()<=max) {
            if(diffSquare && !areFromDiffSquare(target) )
                return EMPTY_SET;
            return target;
            }
        return EMPTY_SET;
    }

    public boolean areFromDiffSquare(Set<Figure> set){
            Square s;
            for (Figure f : target) {
                Set<Figure> temp = new HashSet<>(target);
                temp.remove(f);
                s = f.getPosition();
                for(Figure p : temp){
                    if(s==p.getPosition()){
                        return false;
                    }
                }

            }
            return true;


    }

    public void setTarget( Set<Figure> target) {
        this.target = target;
    }

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}

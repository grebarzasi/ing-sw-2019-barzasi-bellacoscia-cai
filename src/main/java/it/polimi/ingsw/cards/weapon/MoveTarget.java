package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;

import java.util.Set;

/**
 * Effect that moves player around ( also the weapon owner, like and extra move);
 * you can define the final position for the target and also if the move could append in any direction or not;
 * @author Gregorio Barzasi
 */

public class MoveTarget implements SubEffect {

    private int maxSteps;
    private String finalPos;
    private boolean directional;
    private Square squareTemp;
    private Set<Figure> targetTemp;

    public MoveTarget(int maxSteps, String finalPos, boolean directional) {
        this.maxSteps=maxSteps;
        this.finalPos=finalPos;
        this.directional=directional;
        this.targetTemp=null;
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public Set<Figure> applyEffect(Weapon w, Set<Figure> p){
        if(p==null)
            return null;
        switch(finalPos){
            case "last":
                squareTemp = w.getLastHit().getPosition();
                break;
            case "me":
                squareTemp = w.getOwner().getPosition();
                break;
        }
        if(squareTemp==null) {
            if (w.getMoveTemp() == null) {
                targetTemp=p;
                w.setMoveTemp(this);
                return null;
            }
            p=targetTemp;
            w.setMoveTemp(null);
        }

        for (Figure target : p) {
            target.setPosition(squareTemp);
        }
        return p;
    }

    @Override
    public void resetSubEffect() {
        targetTemp=null;
    }

    public void setMaxSteps(int maxSteps) {
        this.maxSteps = maxSteps;
    }

    public String getFinalPos() {
        return finalPos;
    }

    public void setFinalPos(String finalPos) {
        this.finalPos = finalPos;
    }

    public boolean isDirectional() {
        return directional;
    }

    public void setDirectional(boolean directional) {
        this.directional = directional;
    }

    public Square getSquareTemp() {
        return squareTemp;
    }

    public void setSquareTemp(Square squareTemp) {
        this.squareTemp = squareTemp;
    }

    public Set<Figure> getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Set<Figure> targetTemp) {
        this.targetTemp = targetTemp;
    }
}

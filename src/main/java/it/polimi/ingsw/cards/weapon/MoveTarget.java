package it.polimi.ingsw.cards.weapon;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;
import java.util.HashSet;
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
        this.targetTemp=new HashSet<>();
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public Set<Figure> applyEffect(Weapon w, Set<Figure> p){
        if(p==null)
            return null;
        if(p.isEmpty())
            return p;

        switch(finalPos){
            case "last":
                squareTemp = w.getLastHit().getPosition();
                break;
            case "selected":
                squareTemp = w.getSelected();
                break;
            case "me":
                squareTemp = w.getOwner().getPosition();
                break;
            case "far":
                ArrayList<Square> pos=new ArrayList<>();
                for(Figure f: w.getDamaged())
                    pos.add(f.getPosition());
                squareTemp = getFar(w.getOwner(),pos);
                break;

        }

        if(squareTemp==null&&w.getMoveTemp()==null) {
                targetTemp.addAll(p);
                w.setMoveTemp(this);
                return null;
        }else if(w.getMoveTemp()!=null){
            p.clear();
            p.addAll(targetTemp);
            w.setMoveTemp(null);
        }

        for (Figure target : p) {
            target.setOldPosition(target.getPosition());
            target.setPosition(squareTemp);
            w.setLastMoved(target);
        }

        if(finalPos.equals("visible")&&!w.getOwner().canSee(w.getLastMoved())) {
            w.getLastMoved().setPosition(w.getLastMoved().getOldPosition());
            targetTemp.addAll(p);
            w.setMoveTemp(this);
            return null;
        }
        return p;
    }

    @Override
    public void resetSubEffect() {
        targetTemp.clear();
        squareTemp=null;
    }

    public Square getFar(Figure p, ArrayList<Square> all) {
        int max=0;
        Square far=null;
        for(Square s : all)
            if(p.distanceTo(s)>max) {
                max = p.distanceTo(s);
                far = s;
            }
        return far;

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

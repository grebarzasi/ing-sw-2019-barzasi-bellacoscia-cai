package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Cell;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *Used by {@link TargetAcquisition} class to filter target to only ones laying on the cardinal direction selected.
 * Need an input from the player to work
 *
 * @author Gregorio Barzasi
 */
public class AimDirection implements AimingFilter {

    private boolean wallBang;
    private String directionTemp="";
    private Set<Figure> targetTemp;

    public AimDirection(boolean wallBang) {
        this.wallBang = wallBang;
        targetTemp=new HashSet<>();
    }

    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(w.getDirectionTemp()==null){
            w.setDirectionTemp(this);
            targetTemp.addAll(allDirectional(w.getOwner(),p));
            return null;
        }
        w.setDirectionTemp(null);
        if(directionTemp.isEmpty())
            return null;
        String dir = directionTemp;
        if (wallBang)
            return directionAll(dir, w.getOwner(), targetTemp);
        return directionWall(dir, w.getOwner(), targetTemp);
    }

    public Set<Figure> allDirectional(Figure origin, Set<Figure> p){
        Set<Figure> temp = new HashSet<>();
        ArrayList<String> directions= new ArrayList<>(Arrays.asList("n", "s", "e","o"));
        if(wallBang) {
            for (String s : directions) {
                temp.addAll(directionWall(s,origin,p));
            }
        }else{
            for (String s : directions) {
                temp.addAll(directionAll(s,origin,p));            }
        }
        return temp;
    }


    public Set<Figure> directionWall(String dir,Figure origin, Set<Figure> p) {
        Set<Figure> temp = new HashSet<>();
        Square pos = origin.getPosition();
        switch (dir) {
            case "n":
                while(pos!=null) {
                    temp.addAll(pos.playersInSquare(p));
                    pos=pos.getNorth();
                }
                break;
            case "s":
                while(pos!=null){
                    temp.addAll(pos.playersInSquare(p));
                    pos=pos.getSouth();
                }
                break;
            case "e":
                while(pos!=null){
                    temp.addAll(pos.playersInSquare(p));
                    pos=pos.getEast();
                }
                break;
            case "o":
                while(pos!=null){
                    temp.addAll(pos.playersInSquare(p));
                pos=pos.getWest();
            }
                break;
        }
    return temp;
    }

    public Set<Figure> directionAll(String dir,Figure origin, Set<Figure> p) {
        //Remove all player outside a given direction
        Cell c = origin.getPosition().getPosition();
        Set<Figure> temp = new HashSet<>(p);
        for (Figure f : p) {
            Cell cTarget = f.getPosition().getPosition();
            switch (dir) {
                case "n":
                    if (cTarget.getColumn() != c.getColumn() || cTarget.getRow() > c.getRow())
                        temp.remove(f);
                    break;
                case "s":
                    if (cTarget.getColumn() != c.getColumn() || cTarget.getRow() < c.getRow())
                        temp.remove(f);
                    break;
                case "e":
                    if (cTarget.getRow() != c.getRow() || cTarget.getColumn() < c.getColumn())
                        temp.remove(f);
                    break;
                case "o":
                    if (cTarget.getRow() != c.getRow() || cTarget.getColumn() > c.getColumn())
                        temp.remove(f);
                    break;
            }
        }
        return temp;
    }

    public boolean isWallBang() {
        return wallBang;
    }

    public void setWallBang(boolean wallBang) {
        this.wallBang = wallBang;
    }

    public String getDirectionTemp() {
        return directionTemp;
    }

    public void setDirectionTemp(String directionTemp) {
        this.directionTemp = directionTemp;
    }

    public Set<Figure> getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Set<Figure> targetTemp) {
        this.targetTemp = targetTemp;
    }

    public void resetFilter() {
        directionTemp="";
        targetTemp.clear();
    }
}

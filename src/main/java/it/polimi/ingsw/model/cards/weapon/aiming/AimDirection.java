package it.polimi.ingsw.model.cards.weapon.aiming;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.board.map.Cell;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.weapon.TargetAcquisition;
import it.polimi.ingsw.model.cards.weapon.Weapon;

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


    public static final String NORTH = "n";
    public static final String SOUTH = "s";
    public static final String EAST ="e";
    public static final String WEST = "o";
    public static final ArrayList<String> ALL_DIRECTIONS = new ArrayList<>(Arrays.asList(NORTH,SOUTH,EAST,WEST));

    /**
     * indicates that you can shoot through walls
     *
     */
    private boolean wallBang;

    /**
     * temporary saves the direction chosen
     *
     */
    private String directionTemp="";
    /**
     * temporary saves the target received
     *
     */
    private Set<Figure> targetTemp;

    public AimDirection(boolean wallBang) {
        this.wallBang = wallBang;
        targetTemp=new HashSet<>();
    }
    /**
     * filter out all players outside a certain direction
     * if controller already set the preference it goes on to the next filter, if not it pauses itself saving the
     * weapon state and return to controller. once a player set the preferences the state is restored and the effect can
     * continue.
     *
     * @param w is the weapon used
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> filter(Weapon w, Set<Figure> p) {
        if(w.getDirectionTemp()==null){
            targetTemp.clear();
            w.setDirectionTemp(this);
            targetTemp.addAll(allDirectional(w.getOwner(),p));
            directionTemp="";
            return null;
        }
        w.setDirectionTemp(null);
        if(directionTemp.isEmpty())
            return null;
        String dir = directionTemp;
        if (wallBang)
            return directionWallBang(dir, w.getOwner(), targetTemp);
        return directionWallBlock(dir, w.getOwner(), targetTemp);
    }


    /**
     * @return all player hittable on cardinal direction
     * @param origin is the source of direction search
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> allDirectional(Figure origin, Set<Figure> p){
        Set<Figure> temp = new HashSet<>();

        if(wallBang) {
            for (String s : ALL_DIRECTIONS) {
                temp.addAll(directionWallBang(s,origin,p));
            }
        }else{
            for (String s : ALL_DIRECTIONS) {
                temp.addAll(directionWallBlock(s,origin,p));
            }
        }
        return temp;
    }

    /**
     * @return all player laying on a certain direction but not behind walls
     * @param dir is the direction chosen
     * @param origin is the origin of the search
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> directionWallBlock(String dir, Figure origin, Set<Figure> p) {
        Set<Figure> temp = new HashSet<>();
        Square pos = origin.getPosition();
        switch (dir) {
            case NORTH:
                while(pos!=null) {
                    temp.addAll(pos.playersInSquare(p));
                    pos=pos.getNorth();
                }
                break;
            case SOUTH:
                while(pos!=null){
                    temp.addAll(pos.playersInSquare(p));
                    pos=pos.getSouth();
                }
                break;
            case EAST:
                while(pos!=null){
                    temp.addAll(pos.playersInSquare(p));
                    pos=pos.getEast();
                }
                break;
            case WEST:
                while(pos!=null){
                    temp.addAll(pos.playersInSquare(p));
                pos=pos.getWest();
            }
                break;
        }
    return temp;
    }
    /**
     * @return all player laying on a certain direction
     * @param dir is the direction chosen
     * @param origin is the origin of the search
     * @param p is the set of hittable players at that moment
     */
    public Set<Figure> directionWallBang(String dir, Figure origin, Set<Figure> p) {
        //Remove all player outside a given direction
        Cell c = origin.getPosition().getPosition();
        Set<Figure> temp = new HashSet<>(p);
        for (Figure f : p) {
            if(f.getPosition()==null)
                continue;
            Cell cTarget = f.getPosition().getPosition();
            switch (dir) {
                case NORTH:
                    if (cTarget.getColumn() != c.getColumn() || cTarget.getRow() < c.getRow())
                        temp.remove(f);
                    break;
                case SOUTH:
                    if (cTarget.getColumn() != c.getColumn() || cTarget.getRow() > c.getRow())
                        temp.remove(f);
                    break;
                case EAST:
                    if (cTarget.getRow() != c.getRow() || cTarget.getColumn() < c.getColumn())
                        temp.remove(f);
                    break;
                case WEST:
                    if (cTarget.getRow() != c.getRow() || cTarget.getColumn() > c.getColumn())
                        temp.remove(f);
                    break;
            }

        }
        return temp;
    }

    public void setDirectionTemp(String directionTemp) {
        this.directionTemp = directionTemp;
    }

    public Set<Figure> getTargetTemp() {
        return targetTemp;
    }

}

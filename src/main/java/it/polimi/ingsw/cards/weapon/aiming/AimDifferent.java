package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.AimingFilter;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *Used by {@link TargetAcquisition} class to filter target excluding ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */


public class AimDifferent implements AimingFilter {
    private ArrayList<String> sourceList;

    private ArrayList<ArrayList<Player>> returnList;

    public AimDifferent(ArrayList<String> sourceList){
        this.sourceList=sourceList;

    }

    public ArrayList<String> getSourceList() {
        return sourceList;
    }

    public ArrayList<Player> filter() {
        return null;

    }
}
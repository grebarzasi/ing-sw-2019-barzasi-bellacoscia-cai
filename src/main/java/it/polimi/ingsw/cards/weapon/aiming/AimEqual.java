package it.polimi.ingsw.cards.weapon.aiming;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.weapon.AimingFilter;
import it.polimi.ingsw.cards.weapon.TargetAcquisition;

import java.util.ArrayList;
/**
 *Used by {@link TargetAcquisition} class to filter target including only ones in the field "sourceList"
 *
 * @author Gregorio Barzasi
 */
public class AimEqual implements AimingFilter {
    private ArrayList<String> sourceList;
    private ArrayList<ArrayList<Player>> returnList;

    public AimEqual(ArrayList<String> sourceList){
        this.sourceList=sourceList;

    }


    public ArrayList<String> getSourceList() {
        return sourceList;
    }
    public ArrayList<Player> filter() {
        return null;

    }
}
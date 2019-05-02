package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.*;

public class


Armory {

    //list of weapons in an armory

    private ArrayList<Weapon> weaponList;
    private static final int maxCapacity = 3;

    /**
     * Checks if the armory is at maximum capacity of 3
     * @return true if as maximum capacity, false otherwise
     *
     * @author Yuting Cai
     */

    public boolean isFull(){

        return this.weaponList.size() == maxCapacity;
    }

    public Armory(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(ArrayList<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public Armory() {
    }

    public void refillWeapon() {
    }

}

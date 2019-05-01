package it.polimi.ingsw.board;

import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.*;

public class
Armory {

    private ArrayList<Weapon> weaponList;

    public boolean isFull(){

        return this.weaponList.size() == 3;
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

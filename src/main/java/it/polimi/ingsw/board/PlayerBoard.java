package it.polimi.ingsw.board;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.cards.Ammo;
import java.util.*;

/**
 * Playerboard, contains information about ammo, received damage and marks, and points drop upon death
 * @author Yuting Cai
 */

public class PlayerBoard {

    private final Player owner;

    private ArrayList<Token> damage;

    private int[] pointVec;

    private ArrayList<Token> mark;

    private Ammo ammoInventory;

    private static final int maxammo = 3;
    private static final int maxhealth = 11;


    /**
     * Adds a damage token to the player's board
     * @param t the token to apply
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void damage(Token t) {


        if (this.damage.size() < maxhealth - 1 ){

            this.damage.add(t);

        }else if(this.damage.size() < maxhealth){

            this.damage.add(t);
            //kill(this.owner);
        }

        else if (this.damage.size() == maxhealth) {

            this.damage.add(t);
            t.getOwner().getPersonalBoard().addMark(new Token(this.owner));
            //returns an overkill mark token
        }


    }

    /**
     * Adds a damage token to the player's board,
     * also if a player has a marks of the same owner applies the marks
     * @param t the token to apply
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void addDamage(Token t){

        while(this.getMark().contains(t)) {

            this.mark.remove(t);
            this.damage(t);

        }

        this.damage(t);

    }

    /**
     * Resets the damage to zero
     */

    public void resetDamage() {
        this.damage = new ArrayList<>();
    }

    /**
     * Adds a mark token to the player's board
     * @param t the token to apply
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void addMark(Token t){

        this.mark.add(t);

    }

    /**
     * Removes a mark token to the player's board
     * @param t the token to apply
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void removeMark(Token t) {
        this.mark.remove(t);
    }

    /**
     * Adds a Skull to the Player, reducing points dropped upon death
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void addSkull() {

        int i;

        for(i=0;i<4;i++) {

            this.pointVec[i] = this.pointVec[i + 1] ;

        }

    }

    /**
     * Adds an amount of ammo to the player's ammunition inventory
     * @param a the number of ammunition to add
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void addAmmo(Ammo a) {

        a.setRed(a.getRed() + this.owner.getPersonalBoard().getAmmoInventory().getRed());
        a.setRed(a.getBlue() + this.owner.getPersonalBoard().getAmmoInventory().getBlue());
        a.setRed(a.getYellow() + this.owner.getPersonalBoard().getAmmoInventory().getYellow());

        this.owner.getPersonalBoard().setAmmoInventory(a);

        if(this.owner.getPersonalBoard().getAmmoInventory().getRed() > maxammo){
            this.owner.getPersonalBoard().getAmmoInventory().setRed(maxammo);
        }

        if(this.owner.getPersonalBoard().getAmmoInventory().getBlue() > maxammo){
            this.owner.getPersonalBoard().getAmmoInventory().setBlue(maxammo);
        }

        if(this.owner.getPersonalBoard().getAmmoInventory().getYellow() > maxammo){
            this.owner.getPersonalBoard().getAmmoInventory().setYellow(maxammo);
        }
    }

    /**
     * Removes an amount of ammo from the player's ammunition inventory
     * @param a the number of ammunition to remove
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void removeAmmo(Ammo a) {

        Ammo tmp = this.owner.getPersonalBoard().getAmmoInventory();

        tmp.setRed(this.owner.getPersonalBoard().getAmmoInventory().getRed() - a.getRed());
        tmp.setRed(this.owner.getPersonalBoard().getAmmoInventory().getBlue() - a.getBlue());
        tmp.setRed(this.owner.getPersonalBoard().getAmmoInventory().getYellow() - a.getYellow());

    }

    /**
     * Initiates the board with default values
     * @param owner the board's player
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public PlayerBoard(Player owner) {

        this.owner = owner;
        this.damage = new ArrayList<Token>();
        this.pointVec = new int[6];
        this.mark = new ArrayList<Token>();
        this.ammoInventory = new Ammo(0,0,0);

        this.pointVec[0]=8;
        this.pointVec[1]=6;
        this.pointVec[2]=4;
        this.pointVec[3]=2;
        this.pointVec[4]=1;
        this.pointVec[5]=1;

    }

    public PlayerBoard(Player owner, ArrayList<Token> damage, int[] pointVec, ArrayList<Token> marks, Ammo ammoInventory) {

        this.owner = owner;
        this.damage = damage;
        this.pointVec = pointVec;
        this.mark = marks;
        this.ammoInventory = ammoInventory;
    }

    public ArrayList<Token> getDamage() {
        return damage;
    }

    public void setDamage(ArrayList<Token> damage) {
        this.damage = damage;
    }

    public int[] getPointVec() {
        return pointVec;
    }

    public void setPointVec(int[] pointVec) {
        this.pointVec = pointVec;
    }

    public ArrayList<Token> getMark() {
        return mark;
    }

    public void setMark(ArrayList<Token> marks) {
        this.mark = marks;
    }

    public Ammo getAmmoInventory() {
        return ammoInventory;
    }

    public void setAmmoInventory(Ammo ammoInventory) {
        this.ammoInventory = ammoInventory;
    }

    public Player getOwner() {
        return owner;
    }
}
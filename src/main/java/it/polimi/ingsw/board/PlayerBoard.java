package it.polimi.ingsw.board;

import it.polimi.ingsw.GameControllerServer;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.cards.Ammo;
import java.util.*;

public class PlayerBoard {

    private GameControllerServer server;

    private ArrayList<Token> damage;

    private int[] pointVec;

    private ArrayList<Token> mark;

    private Ammo ammoInventory;


    /**
     * Adds a damage token to the player's board
     * @param t the token to apply
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void addDamage(Token t) {

        if(this.damage.size() <= 12) {
            this.damage.add(t);
        } else{
            t.getOwner().getMyBoard().addMark(new Token(this.server.getCurrentPlayer()));
        }
    }

    /**
     * Resets the damage to zero
     */

    public void resetDamage() {
        this.damage = new ArrayList<Token>();
    }

    /**
     * Adds a mark token to the player's board
     * @param t the token to apply
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void addMark(Token t) {
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

        Ammo tmp = a;

        tmp.setRed(a.getRed() + this.server.getCurrentPlayer().getMyBoard().getAmmoInventory().getRed());
        tmp.setRed(a.getBlue() + this.server.getCurrentPlayer().getMyBoard().getAmmoInventory().getBlue());
        tmp.setRed(a.getYellow() + this.server.getCurrentPlayer().getMyBoard().getAmmoInventory().getYellow());

        this.server.getCurrentPlayer().getMyBoard().setAmmoInventory(tmp);
    }

    /**
     * Removes an amount of ammo from the player's ammunition inventory
     * @param a the number of ammunition to remove
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public void removeAmmo(Ammo a) {
        Ammo tmp = this.server.getCurrentPlayer().getMyBoard().getAmmoInventory();

        tmp.setRed(this.server.getCurrentPlayer().getMyBoard().getAmmoInventory().getRed() - a.getRed());
        tmp.setRed(this.server.getCurrentPlayer().getMyBoard().getAmmoInventory().getBlue() - a.getBlue());
        tmp.setRed(this.server.getCurrentPlayer().getMyBoard().getAmmoInventory().getYellow() - a.getYellow());
    }

    /**
     * Initiates the board with default values
     * @param server the current server
     *
     * NEEDS TO BE REVISITED, USE AT YOUR OWN AND OTHERS' RISK
     */

    public PlayerBoard(GameControllerServer server) {
        this.server = server;
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

    public PlayerBoard(ArrayList<Token> damage, int[] pointVec, ArrayList<Token> marks, Ammo ammoInventory) {
        this.damage = damage;
        this.pointVec = pointVec;
        this.mark = mark;
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

    public ArrayList<Token> getMarks() {
        return mark;
    }

    public void setMarks(ArrayList<Token> marks) {
        this.mark = marks;
    }

    public Ammo getAmmoInventory() {
        return ammoInventory;
    }

    public void setAmmoInventory(Ammo ammoInventory) {
        this.ammoInventory = ammoInventory;
    }
}
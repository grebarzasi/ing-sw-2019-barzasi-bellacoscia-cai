package it.polimi.ingsw.virtual_model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represent a virtual version of server-side player
 * @author Gregorio Barzasi
 */
public class VirtualPlayer {
    private String username;
    private String character;
    private boolean printed;
    private int points;
    private String pos;
    private HashMap<String,Boolean> weapons;
    private HashMap<String,String> powerUps;
    private VirtualPlayerBoard pBoard;



    public VirtualPlayer(String username,String character){
        this.character=character;
        this.username=username;
        this.printed=false;
        this.pBoard=new VirtualPlayerBoard();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String charachter) {
        this.character = charachter;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public HashMap<String,Boolean> getWeapons() {
        return weapons;
    }

    public void setWeapons(HashMap<String,Boolean>  weapons) {
        this.weapons = weapons;
    }

    public HashMap<String,String>  getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(HashMap<String,String>  powerUps) {
        this.powerUps = powerUps;
    }

    public VirtualPlayerBoard getpBoard() {
        return pBoard;
    }

    public void setpBoard(VirtualPlayerBoard pBoard) {
        this.pBoard = pBoard;
    }
}

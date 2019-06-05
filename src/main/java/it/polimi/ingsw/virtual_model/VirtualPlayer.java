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
    private int row;
    private int column;
    private boolean printed;
    private int points;
    private HashMap<String,Boolean> weapons;
    private  ArrayList<String> powerUps;
    private VirtualPlayerBoard pBoard;



    public VirtualPlayer(String username,String character){
        this.character=character;
        this.username=username;
        this.printed=false;
        this.pBoard=new VirtualPlayerBoard();
        this.weapons = new HashMap<>();
        this.powerUps= new ArrayList<>();
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

    public void setCharacter(String character) {
        this.character = character;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public HashMap<String,Boolean> getWeapons() {
        return weapons;
    }

    public void setWeapons(HashMap<String,Boolean>  weapons) {
        this.weapons = weapons;
    }

    public  ArrayList<String>  getPowerUps() {
        return powerUps;
    }

    public void setPowerUps( ArrayList<String>  powerUps) {
        this.powerUps = powerUps;
    }

    public VirtualPlayerBoard getpBoard() {
        return pBoard;
    }

    public void setpBoard(VirtualPlayerBoard pBoard) {
        this.pBoard = pBoard;
    }
}

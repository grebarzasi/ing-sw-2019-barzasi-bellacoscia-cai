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
//    private HashMap<String,Boolean> weapons;
    private ArrayList<String> weapons;
    private  ArrayList<String> powerUps;
    private VirtualPlayerBoard pBoard;
    private boolean inactive;
    private boolean disconnected;



    public VirtualPlayer(String username,String character){
        this.character=character;
        this.username=username;
        this.printed=false;
        this.pBoard=new VirtualPlayerBoard();
        this.weapons = new ArrayList<>();
        this.powerUps= new ArrayList<>();
    }


    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
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

    public  ArrayList<String> getWeapons() {
        return weapons;
    }

    public void setWeapons( ArrayList<String> weapons) {
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

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean disconnected) {
        this.disconnected = disconnected;
    }
}

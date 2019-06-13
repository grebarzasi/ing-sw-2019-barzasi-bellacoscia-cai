package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

public class VirtualGame {

    private String targetSquare = "";
    private String targetPlayer = "";

    private String weapon = "";
    private String powerup = "";
    private String effect = "";
    private String direction = "";

    private ArrayList<String> hideSquare = new ArrayList<>();
    private ArrayList<String> ammoTiles = new ArrayList<>();

    private ArrayList<String> actions = new ArrayList<>();
    private String action = "";


    public VirtualGame() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public synchronized String getDirection() {
        return direction;
    }

    public synchronized void setDirection(String direction) {
        this.direction = direction;
    }

    public synchronized ArrayList<String> getActions() {
        return actions;
    }

    public synchronized void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    public synchronized String getTargetSquare() {
        return targetSquare;
    }

    public synchronized void setTargetSquare(String targetSquare) {
        this.targetSquare = targetSquare;
    }

    public synchronized String getTargetPlayer() {
        return targetPlayer;
    }

    public synchronized void setTargetPlayer(String targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public synchronized String getWeapon() {
        return weapon;
    }

    public synchronized void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public synchronized String getPowerup() {
        return powerup;
    }

    public synchronized void setPowerup(String powerup) {
        this.powerup = powerup;
    }

    public synchronized String getEffect() {
        return effect;
    }

    public synchronized void setEffect(String effect) {
        this.effect = effect;
    }

    public synchronized ArrayList<String> getHideSquare() {
        return hideSquare;
    }

    public synchronized void setHideSquare(ArrayList<String> hideSquare) {
        this.hideSquare = hideSquare;
    }

    public synchronized ArrayList<String> getAmmoTiles() {
        return ammoTiles;
    }

    public synchronized void setAmmoTiles(ArrayList<String> ammoTiles) {
        this.ammoTiles = ammoTiles;
    }
}

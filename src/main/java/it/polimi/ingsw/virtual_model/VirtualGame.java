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

    public VirtualGame() {
        hideSquare.add("0:0");
        hideSquare.add("0:1");
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

    public String getTargetSquare() {
        return targetSquare;
    }

    public void setTargetSquare(String targetSquare) {
        this.targetSquare = targetSquare;
    }

    public String getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(String targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getPowerup() {
        return powerup;
    }

    public void setPowerup(String powerup) {
        this.powerup = powerup;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public ArrayList<String> getHideSquare() {
        return hideSquare;
    }

    public void setHideSquare(ArrayList<String> hideSquare) {
        this.hideSquare = hideSquare;
    }

    public ArrayList<String> getAmmoTiles() {
        return ammoTiles;
    }

    public void setAmmoTiles(ArrayList<String> ammoTiles) {
        this.ammoTiles = ammoTiles;
    }
}

package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

public class VirtualGame {

    private String targetSquare;
    private String targetPlayer;

    private String weapon;
    private String powerup;
    private String effect;

    private ArrayList<String> hideSquare = new ArrayList<>();
    private ArrayList<String> ammoTiles = new ArrayList<>();

    public VirtualGame() {
        hideSquare.add("2:1");
        hideSquare.add("2:2");
        hideSquare.add("2:3");
        hideSquare.add("1:2");
        hideSquare.add("1:3");
        hideSquare.add("0:3");

        ammoTiles.add("YBB");
        ammoTiles.add("PRB");
        ammoTiles.add("YBB");
        ammoTiles.add("PRB");
        ammoTiles.add("YBB");
        ammoTiles.add("PRB");
        ammoTiles.add("YBB");
        ammoTiles.add("PRB");
        ammoTiles.add("YBB");
        ammoTiles.add("PRB");
        ammoTiles.add("YBB");
        ammoTiles.add("PRB");
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

package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.board.map.Square;

public class VirtualGame {

    private String targetSquare;
    private String targetPlayer;

    private String weapon;
    private String powerup;
    private String effect;

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
}
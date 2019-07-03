package it.polimi.ingsw.model.cards;

/**
 * Parses an Ammo lot
 * @author Yuting Cai
 */

public class AmmoParser {

    private boolean powerup;

    private int red;
    private int yellow;
    private int blue;


    public boolean hasPowerup() {
        return powerup;
    }

    public void setPowerup(boolean powerup) {
        this.powerup = powerup;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getYellow() {
        return yellow;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }


}

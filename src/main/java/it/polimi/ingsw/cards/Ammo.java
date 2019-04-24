package it.polimi.ingsw.cards;
/**
 * @author Gregorio Barzasi
 */
public class Ammo extends CarePackage {

    private int red;
    private int blue;
    private int yellow;

    public Ammo(int red,int blue,int yellow) {
        this.red=red;
        this.yellow=yellow;
        this.blue=blue;
    }

    public Ammo() {
        super();
    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public int getYellow() {
        return yellow;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public void setYellow(int yellow) {
        this.yellow = yellow;
    }
}
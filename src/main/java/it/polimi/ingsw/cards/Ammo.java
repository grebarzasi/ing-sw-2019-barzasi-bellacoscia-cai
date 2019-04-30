package it.polimi.ingsw.cards;
/**
 * @author Gregorio Barzasi
 */
public class Ammo{

    private int red;
    private int blue;
    private int yellow;

    public Ammo(int red,int blue,int yellow) {
        this.red=red;
        this.blue=blue;
        this.yellow=yellow;
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
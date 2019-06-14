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

    public String toString(){
        String s="";
//        if(yellow==1)
//            return "yellow";
//        if(blue==1)
//            return "blue";
//        return "red";
        for(int i=0;i<yellow;i++)
            s=s+"Y";
        for(int i=0;i<blue;i++)
            s=s+"B";
        for(int i=0;i<red;i++)
            s=s+"R";
        return s;
    }
}
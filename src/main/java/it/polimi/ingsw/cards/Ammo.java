package it.polimi.ingsw.cards;


/**
 * Class that represents the ammo in game. used widely in model is one of the main component of the game.
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
        for(int i=0;i<yellow;i++)
            s=s+"Y";
        for(int i=0;i<blue;i++)
            s=s+"B";
        for(int i=0;i<red;i++)
            s=s+"R";
        return s;
    }

    /**
     * Checks if an ammo covers the reload cost of another ammo object
     *
     * @param a ammunition cost
     * @return true if it the cost can be covered, false otherwise
     */
    public boolean covers(Ammo a) {

        if (this.getBlue() - a.getBlue() < 0 || this.getRed() - a.getRed() < 0 || this.getYellow() - a.getYellow() < 0) {
            return false;
        }
        return true;
    }

    public boolean isEmpty(){

        if(this.red == 0 && this.blue == 0 && this.yellow == 0){
            return true;
        }else{
            return false;
        }

    }

    public void subtract(Ammo a){
        this.red = this.red - a.red;
        this.blue = this.blue - a.blue;
        this.yellow = this.yellow - a.yellow;
    }
}
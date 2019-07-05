package it.polimi.ingsw.model.cards;


/**
 * Class that represents the ammo in game. used widely in model is one of the main component of the game.
 * @author Gregorio Barzasi
 */
public class Ammo{

    /**
     * Number of red ammunition
     */
    private int red;

    /**
     * Number of blue ammunition
     */
    private int blue;

    /**
     * Number of yellow ammunition
     */
    private int yellow;

    /**
     * Default Cnstructor
     * @param red amount of red ammunition
     * @param blue amount of blue ammunition
     * @param yellow amount of yellow ammunition
     */
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

    /**
     * converts the ammunition into a parsable String
     * @return the coded Ammo
     */
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

        return this.getBlue() - a.getBlue() >= 0 && this.getRed() - a.getRed() >= 0 && this.getYellow() - a.getYellow() >= 0;
    }

    /**
     * Checks if the ammo is an empty one
     * @return true if this is empty, false otherwise
     */
    public boolean isEmpty(){

        return this.red == 0 && this.blue == 0 && this.yellow == 0;

    }

    /**
     * Substracts another Ammo from this
     * @param a this after removing a from this
     */
    public void subtract(Ammo a){
        this.red = this.red - a.red;
        this.blue = this.blue - a.blue;
        this.yellow = this.yellow - a.yellow;
    }
    
    public Ammo unchambered(Ammo chamber){
        Ammo tmp = new Ammo(this.red,this.blue,this.yellow);
        
        tmp.red = tmp.red - chamber.red;
        tmp.blue = tmp.blue - chamber.blue;
        tmp.yellow = tmp.yellow - chamber.yellow;

        return tmp;
        
    }
}
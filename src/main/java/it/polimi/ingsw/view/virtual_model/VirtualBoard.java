package it.polimi.ingsw.view.virtual_model;


import java.util.ArrayList;
/**
 *Represent The board client side.
 * */
public class VirtualBoard {
    /**
     *number of skulls on board
     */
    private int skull;
    /**
     *killshot track info saved under the form of arraylist of string made like this:
     * char;char; (char = the color of the player ) where every place indicates a token
     */
    private ArrayList<String> killshotTrack=new ArrayList<>();
    /**
     *game map
     */
    private VirtualMap map = new VirtualMap();

    public int getSkull() {
        return skull;
    }

    public void setSkull(int skull) {
        this.skull = skull;
    }

    public ArrayList<String> getKillshotTrack() {
        return killshotTrack;
    }

    public void setKillshotTrack(ArrayList<String> killshotTrack) {
        this.killshotTrack = killshotTrack;
    }

    public VirtualMap getMap() {
        return map;
    }

    public void setMap(VirtualMap map) {
        this.map = map;
    }

}

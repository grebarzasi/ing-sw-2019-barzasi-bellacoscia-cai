package it.polimi.ingsw.board;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;

import java.util.*;

/**
 * The type Killshot track.
 *
 * @author Carlo Bellacoscia
 */
public class KillshotTrack {

    private int skullMax;
    private ArrayList<ArrayList <Token>> killsTrack = new ArrayList<>();

    /**
     * Instantiates a new Killshot track.
     *
     * @param skullMax the skull max
     */
    public KillshotTrack(int skullMax) {
        this.skullMax = skullMax;
    }

    /**
     * Gets skull max.
     *
     * @return the skull max
     */
    public int getSkullMax() {
        return skullMax;
    }

    /**
     * Sets skull max.
     *
     * @param skullMax the skull max
     */
    public void setSkullMax(int skullMax) {
        this.skullMax = skullMax;
    }

    /**
     * Gets kills track.
     *
     * @return the kills track
     */
    public ArrayList<ArrayList<Token>> getKillsTrack() {
        return killsTrack;
    }

    /**
     * Sets kills track.
     *
     * @param killsTrack the kills track
     */
    public void setKillsTrack(ArrayList<ArrayList<Token>> killsTrack) {
        this.killsTrack = killsTrack;
    }

    /**
     * Remove skull.
     */
    public void removeSkull() {
        this.skullMax --;
    }

    /**
     * Add kill.
     *
     * @param token the token
     */
    public void addKill(ArrayList <Token> token) {
        killsTrack.add(token);
    }

    /**
     * Gets points.
     */
    public void getPoints() {
        if(killsTrack != null) {
            ArrayList<Player> playerList = killerListCreator();
            ArrayList<Integer> occ = new ArrayList<>();

            for (Player p : playerList) {
                occ.add(countOcc(p));
            }

            switch (playerList.size()) {
                case 5: {
                    playerList.get(getIndexMax(occ)).addPoints(8);
                    playerList.get(getIndexMax(occ)).addPoints(6);
                    playerList.get(getIndexMax(occ)).addPoints(4);
                    playerList.get(getIndexMax(occ)).addPoints(2);
                    playerList.get(getIndexMax(occ)).addPoints(2);
                    break;
                }
                case 4:{
                    playerList.get(getIndexMax(occ)).addPoints(8);
                    playerList.get(getIndexMax(occ)).addPoints(6);
                    playerList.get(getIndexMax(occ)).addPoints(4);
                    playerList.get(getIndexMax(occ)).addPoints(2);
                    break;
                }
                case 3:{
                    playerList.get(getIndexMax(occ)).addPoints(8);
                    playerList.get(getIndexMax(occ)).addPoints(6);
                    playerList.get(getIndexMax(occ)).addPoints(4);
                    break;
                }
                case 2:{
                    playerList.get(getIndexMax(occ)).addPoints(8);
                    playerList.get(getIndexMax(occ)).addPoints(6);
                    break;
                }
                case 1:{
                    playerList.get(getIndexMax(occ)).addPoints(8);
                    break;
                }
            }
        }

    }


    /**
     * Killer list creator array list.
     *
     * @return the array list
     */
    public ArrayList<Player> killerListCreator() {
        ArrayList<Player> playerList = new ArrayList<>();

        for (ArrayList<Token> i : killsTrack) {
            for (Token t : i) {
                if (!playerList.contains(t.getOwner())) {
                    playerList.add(t.getOwner());
                }
            }
        }
        return playerList;
    }


    /**
     * Count occ integer.
     *
     * @param p the p
     * @return the integer
     */
    public Integer countOcc (Player p){

            Token token = new Token(p);
            Integer occ = 0;

            for (ArrayList<Token> i : killsTrack) {
                if (i.equals(token)) {
                    occ++;
                }

            }
            return occ;
        }


    /**
     * Get index max int.
     *
     * @param occ the occ
     * @return the int
     */
    public int getIndexMax(ArrayList<Integer> occ){
        int maxIndex = 0;
        Integer max = 0;

        for (Integer i: occ) {
            if(occ.get(i) != null && occ.get(i) > max){
                max = occ.get(i);
                maxIndex = occ.indexOf(occ.get(i));
                occ.remove(occ.get(i));
            }
        }
        return maxIndex;
    }



}

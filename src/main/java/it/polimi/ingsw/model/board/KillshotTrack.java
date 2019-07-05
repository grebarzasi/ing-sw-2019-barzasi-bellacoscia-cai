package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.Figure;
import it.polimi.ingsw.model.Token;

import java.util.*;

/**
 * Killshot track
 * Assigns the kill scores at the end of the game,
 * manages disparity as per playerboard scoring system
 */

public class KillshotTrack {

    private static final int[] KILLSTRACK_POINTS_VEC = {8,6,4,2,1};

    private int skullMax;

    private ArrayList<ArrayList <Token>> killsTrack = new ArrayList<>();

    public KillshotTrack(int skullMax) {
        this.skullMax = skullMax;
    }

    public int getSkullMax() {
        return skullMax;
    }

    public void setSkullMax(int skullMax) {
        this.skullMax = skullMax;
    }

    public ArrayList<ArrayList<Token>> getKillsTrack() {
        return killsTrack;
    }

    public void removeSkull() {
        skullMax --;
    }

    public void addKill(ArrayList <Token> token) {
        killsTrack.add(token);
        removeSkull();
    }


    /**
     * Assigns the points according to the game rules to the players
     * priority checked using damagePriority method
     */

    public void scorePoints(){

        HashMap<Figure,Integer> killsPerPlayer = this.mapKillsPerPlayer();

        ArrayList<Figure> orderedList = new ArrayList<>();
        ArrayList<Figure> toAdd = new ArrayList<>();
        toAdd.addAll(killsPerPlayer.keySet());

        for(Figure f: toAdd){

            int i;
            boolean added = false;

            if(orderedList.isEmpty()){
                orderedList.add(f);
            }else {

                for (i = 0; i < orderedList.size(); i++) {
                    if (killsPerPlayer.get(f) >= killsPerPlayer.get(orderedList.get(i)) && !added){
                        orderedList.add(i,f);
                        added = true;
                    }
                }
                if(!added){
                    orderedList.add(f);
                }

            }
        }

        int i;
        int k;

        for(i = 0 ; i< orderedList.size() ; i++){
            for(k = 0 ; k < orderedList.size() ; k++){
                if(killsPerPlayer.get(orderedList.get(k)) == killsPerPlayer.get(orderedList.get(i)) && damagePriority(orderedList.get(k)) > damagePriority(orderedList.get(i))){

                    Figure temp = orderedList.get(i);
                    orderedList.set(i,orderedList.get(k));
                    orderedList.set(k,temp);

                }
            }
        }

        for(i=0;i<orderedList.size();i++){

            orderedList.get(i).addPoints(KILLSTRACK_POINTS_VEC[i]);

        }

   }

   public HashMap<Figure, Integer> mapKillsPerPlayer(){

       HashMap<Figure,Integer> killsPerPlayer = new HashMap<>();

       for(ArrayList<Token> kill : this.killsTrack){
           for(Token t: kill){
               if(!killsPerPlayer.containsKey(t.getOwner())){
                   killsPerPlayer.put(t.getOwner(),1);
               }else{
                   killsPerPlayer.replace(t.getOwner(),killsPerPlayer.get(t.getOwner())+1);
               }
           }
       }

       return killsPerPlayer;
   }

    /**
     * private method used by scorePoints to determine priority in case of token parity on the killshot track
     * @param f the player inquired
     * @return position of the first token of the player f on the killshot track
     */

   private int damagePriority(Figure f){

        int priority = 0;

        for(ArrayList<Token> kill: this.killsTrack){
            for(Token t: kill){
                if(f.equals(t.getOwner())){
                    return priority;
                }
                priority ++;
            }
        }

        return 0;
   }

}

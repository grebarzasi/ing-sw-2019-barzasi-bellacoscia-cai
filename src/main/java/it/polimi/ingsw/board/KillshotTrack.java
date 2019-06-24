package it.polimi.ingsw.board;

import it.polimi.ingsw.Figure;
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

    public void setKillsTrack(ArrayList<ArrayList<Token>> killsTrack) {
        this.killsTrack = killsTrack;
    }

    public void removeSkull() {
        skullMax --;
    }

    public void addKill(ArrayList <Token> token) {
        killsTrack.add(token);
        removeSkull();
    }

    public void getPoints() {

        if(killsTrack != null) {
            ArrayList<Figure> figureList = killerListCreator();
            ArrayList<Integer> occ = new ArrayList<>();

            for (Figure p : figureList) {
                occ.add(countOcc(p));
            }

            switch (figureList.size()) {

                case 5: {
                    figureList.get(getIndexMin(occ)).addPoints(2);
                }
                case 4:{
                    figureList.get(getIndexMin(occ)).addPoints(2);
                }
                case 3:{
                    figureList.get(getIndexMin(occ)).addPoints(4);
                }
                case 2:{
                    figureList.get(getIndexMin(occ)).addPoints(6);
                }
                case 1:{
                    figureList.get(getIndexMin(occ)).addPoints(8);
                }
            }

        }

    }


    public ArrayList<Figure> killerListCreator() {
        ArrayList<Figure> figureList = new ArrayList<>();

        for (ArrayList<Token> i : killsTrack) {
            for (Token t : i) {
                if (!figureList.contains(t.getOwner())) {
                    figureList.add(t.getOwner());
                }
            }
        }
        return figureList;
    }


    public Integer countOcc (Figure p){

            Token token = new Token(p);
            Integer occ = 0;

            for (ArrayList<Token> i : killsTrack) {
                for (Token j:i) {
                    if (equals(j, token)) {
                        occ++;
                    }
                }

            }
            return occ;
        }


    public int getIndexMin(ArrayList<Integer> occ){
        int minIndex = 0;
        Integer min = 20;

        for (Integer i = 0; i < occ.size(); i++) {
            if(occ.get(i) != -1 && occ.get(i) < min){
                min = occ.get(i);
                minIndex = i;
            }
        }
        occ.set(minIndex, -1);
        if(occ.contains(min)){
            if(occ.indexOf(min) < minIndex) {
                minIndex = occ.indexOf(min);
            }
        }
        return minIndex;
    }

    public boolean equals(Token i, Token t){
        if(t.getOwner() == i.getOwner()){
            return true;
        }
        return false;
    }

}

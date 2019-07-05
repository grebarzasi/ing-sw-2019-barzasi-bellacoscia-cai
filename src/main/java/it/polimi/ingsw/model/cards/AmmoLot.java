package it.polimi.ingsw.model.cards;


public class AmmoLot extends Card {


    private boolean powerup;

    private Ammo content;

    AmmoLot(boolean powerup, Ammo content) {
        this.powerup = powerup;
        this.content = content;
    }

    public boolean hasPowerup() {
        return powerup;
    }

    public void setPowerup(boolean powerup) {
        this.powerup = powerup;
    }

    public Ammo getContent() {
        return content;
    }

    public String toString(){
        String s="";
        if(powerup){
            s="P";
        }
        for(int i =0; i<content.getRed(); i++){
            s=s+"R";
        }
        for(int i =0; i<content.getBlue(); i++){
            s=s+"B";
        }
        for(int i =0; i<content.getYellow(); i++){
            s=s+"Y";
        }
        return s;
    }
}
package it.polimi.ingsw.cards;

public class AmmoLot extends Card {


    private boolean powerup;

    private Ammo content;



    public void useCare() {
    }

    public void fetch(){

    }

    public AmmoLot(boolean powerup, Ammo content) {
        this.powerup = powerup;
        this.content = content;
    }
}
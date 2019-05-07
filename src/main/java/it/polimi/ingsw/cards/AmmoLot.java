package it.polimi.ingsw.cards;

public class AmmoLot extends Card {


    private boolean powerup;

    private Ammo content;



    public void useCare() {
    }

    public AmmoLot(boolean powerup, Ammo content) {
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

    public void setContent(Ammo content) {
        this.content = content;
    }
}
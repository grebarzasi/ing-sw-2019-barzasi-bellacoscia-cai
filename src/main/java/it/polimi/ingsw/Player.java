package it.polimi.ingsw;

import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * A non bot human controlled player in the game.
 * can perform normal actions
 */


public class Player extends Figure {


    //chosen username

    private String username;

    //list of weapons available to the player

    private ArrayList<Weapon> weaponsList;

    //list of powerups available to the player

    private ArrayList<PowerUp> powerupList;

    /**
     * Picks up AmmoLot on the square
     *
     * MISSING MAXIMUM POWERUP MANAGEMENT
     */


    public void pick(){

        if(!this.getPosition().isSpawn()){
            this.pickAmmo();
        }else if(this.getPosition().isSpawn()){
            //pickWeapon();
        }

    }

    public void pickAmmo(){

        if(this.getPosition().isSpawn() || (!this.getPosition().isSpawn() && ((NonSpawnSquare)this.getPosition()).getDrop() == null)){
            System.out.println("No ammo to pick \n");
        }else{

            if(this.getPosition().isSpawn() || ((NonSpawnSquare)this.getPosition()).getDrop().hasPowerup()){
                this.powerupList.add((PowerUp)this.getControllerServer().getCurrentBoard().getPowerupDeck().fetch());
            }
            if(!this.getPosition().isSpawn()){
                Ammo tmp = ((NonSpawnSquare)this.getPosition()).getDrop().getContent();
                this.getPersonalBoard().addAmmo(tmp);
                ((NonSpawnSquare)this.getPosition()).setDrop(null);
            }
        }
    }

    public void pickWeapon(Weapon selection){

    }

    public Player(String username, String character) {

        super(character);
        this.username = username;
        this.weaponsList = new ArrayList<>();
        this.powerupList = new ArrayList<>();

    }

    public Player(String username, String character, Square position) {

        super(character, position);
        this.username = username;
        this.weaponsList = new ArrayList<>();
        this.powerupList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    public void setWeaponsList(ArrayList<Weapon> weaponsList) {
        this.weaponsList = weaponsList;
    }

    public ArrayList<PowerUp> getPowerupList() {
        return powerupList;
    }

    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.powerupList = powerupList;
    }
}

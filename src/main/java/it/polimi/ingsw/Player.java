package it.polimi.ingsw;

import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;


public class Player extends Figure {


    //chosen username

    private String username;

    //list of weapons available to the player

    private ArrayList<Weapon> weaponsList;

    //list of powerups available to the player

    private ArrayList<PowerUp> powerupList;

    public void pickAmmo(){

        if(this.getPosition().isSpawn()){
            System.out.println("no ammo to pick");
        }
        if(!this.getPosition().isSpawn()){
            Ammo tmp = ((NonSpawnSquare)this.getPosition()).getDrop().getContent();
            this.getPersonalBoard().addAmmo(tmp);
        }

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

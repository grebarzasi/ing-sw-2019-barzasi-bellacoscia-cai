package it.polimi.ingsw;

import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Preferences;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * A non bot human controlled player in the game.
 * can perform normal actions
 */


public class Player extends Figure {

    private static final int MAX_PU = 3;

    private Preferences pref;

    private View view;
    //chosen username

    private String username;

    //list of weapons available to the player

    private ArrayList<Weapon> weaponsList;

    //list of powerups available to the player

    private ArrayList<PowerUp> powerupList;

    private int maxActions;


    private boolean disconnected;
    private boolean inactive;

    public Preferences getPref() {
        return pref;
    }

    public void setPref(Preferences pref) {
        this.pref = pref;
    }

    public Player(){
        super();
        this.weaponsList = new ArrayList<>();
        this.powerupList = new ArrayList<>();
    }

    /**
     * Picks up AmmoLot on the square
     * Maximum powerup case managed in controller;
     */

    public PowerUp pickAmmo(){

        if(!this.getPosition().isSpawn()) {

            Ammo tmp = ((NonSpawnSquare) this.getPosition()).getDrop().getContent();
            AmmoLot lotTemp = ((NonSpawnSquare)this.getPosition()).getDrop();

            this.getPersonalBoard().addAmmo(tmp);
            ((NonSpawnSquare)this.getPosition()).setDrop(null);

            this.getModel().getBoard().getAmmoDeck().discard(lotTemp);

            if (lotTemp.hasPowerup()) {
                if (this.getPowerupList().size() < MAX_PU) {
                    this.addPowerUp((PowerUp)this.getModel().getBoard().getPowerupDeck().fetch());
                    return null;
                } else {
                    return (PowerUp) this.getModel().getBoard().getPowerupDeck().fetch();
                }
            }else{
                return null;
            }
        } else {
            return null;
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

    public void addPowerUp(PowerUp toAdd) {
        this.getPowerupList().add(toAdd);
    }

    public void removePowerUp(PowerUp toRemove) {
        this.powerupList.remove(toRemove);
    }

    public void addWeapon(Weapon weaponToAdd){
        this.weaponsList.add(weaponToAdd);
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

    public String getCharacter(){
        return super.getCharacter();
    }

    public void setCharacter(String character){
        super.setCharacter(character);
    }

    public int getMaxActions() {
        return maxActions;
    }

    public void setMaxActions(int maxActions) {
        this.maxActions = maxActions;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean disconnected) {
        this.disconnected = disconnected;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }
}

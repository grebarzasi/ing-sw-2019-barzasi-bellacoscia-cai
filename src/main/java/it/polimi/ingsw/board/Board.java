package it.polimi.ingsw.board;


import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Room;
import it.polimi.ingsw.board.map.SpawnSquare;
import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;
import it.polimi.ingsw.cards.power_up.PowerupDeckBuilder;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.cards.weapon.weapon_builder.WeaponDeckBuilder;


import java.util.ArrayList;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;

/**
 * Represents the entire board of the game,
 * has map, the killshot track and the decks.
 * @author Yuting Cai
 */

public class Board{

    private Map map;
    private KillshotTrack track;

    private Deck ammoDeck;
    private Deck powerupDeck;
    private WeaponDeck weaponDeck;




    /**
     * Constructor of the board, initiates the map according to the chosen selection
     * Initiates all the shuffled decks,
     * fills the ammo lot drops on the maps and the armories
     *
     * @param selection the Map selection to load from json file.
     *
     * @author Yuting Cai
     */

    public Board(String selection){

        this.track=new KillshotTrack(8);
        //loads the map
        this.map = new Map(selection);

        //loads the ammo deck and shuffles it
        this.ammoDeck = new Deck();

        loadDeck(this.ammoDeck);
        this.ammoDeck.shuffle();

        //loads the weapon deck and shuffles it
        this.weaponDeck = WeaponDeckBuilder.buildDeck();
        this.weaponDeck.shuffle();

        //loads the powerup deck and shuffles it
        PowerupDeckBuilder tmpDeck = new PowerupDeckBuilder();
        this.powerupDeck = tmpDeck.PowerUpBuilder();
        this.powerupDeck.shuffle();


        int row;
        int column;


        //loads the ammolots into the squares without a respawn spot
        for(row = 0 ; row < Map.HEIGHT; row ++){
            for(column = 0; column < Map.WIDTH ; column++){

                if(!this.map.getSquareMatrix()[row][column].isSpawn() && !this.map.getSquareMatrix()[row][column].getRoom().getColor().equals(Room.VOID)){

                    ((NonSpawnSquare)this.map.getSquareMatrix()[row][column]).setDrop((AmmoLot)this.ammoDeck.fetch());

                }
            }
        }

        //loads the weapons into the armories

        for(row = 0 ; row < Map.HEIGHT; row ++){
            for(column = 0; column < Map.WIDTH ; column++){

                if(this.map.getSquareMatrix()[row][column].isSpawn()){

                    SpawnSquare tmp = (SpawnSquare)this.map.getSquareMatrix()[row][column];

                    tmp.setArmory(new Armory(new ArrayList<>()));

                    while(!((SpawnSquare)this.getMap().getSquareMatrix()[row][column]).getArmory().isFull()) {
                        ((SpawnSquare)this.getMap().getSquareMatrix()[row][column]).getArmory().getWeaponList().add((Weapon)this.weaponDeck.fetch());
                    }

                    this.map.getSquareMatrix()[row][column] = tmp;

                }
            }
        }
    }

    /**
     * refills all the squares with ammo or weapons according to their type
     * @author Yuting Cai
     */

    public void refillSquares() {

        this.fillAmmoLots();
        this.fillArmories();
    }

    public void fillAmmoLots(){

        for (int row = 0; row < Map.HEIGHT; row++) {
            for (int column = 0; column < Map.WIDTH; column++) {

                //if the square is not a respawn spot and if its drop has been collected
                if (!this.getMap().getSquareMatrix()[row][column].isSpawn() && ((NonSpawnSquare)this.getMap().getSquareMatrix()[row][column]).getDrop() ==null
                        && !this.getMap().getSquareMatrix()[row][column].getRoom().getColor().equals(Room.VOID)) {

                    //refill the drop
                    ((NonSpawnSquare) this.getMap().getSquareMatrix()[row][column]).setDrop((AmmoLot)this.ammoDeck.fetch());
                }

            }
        }
    }

    public void fillArmories() {

        for (int row = 0; row < Map.HEIGHT; row++) {
            for (int column = 0; column < Map.WIDTH; column++) {
                //for each cell of the square matrix if it is a spawn square
                if (this.getMap().getSquareMatrix()[row][column].isSpawn()) {

                    //while it is not full
                    while (!((SpawnSquare) this.getMap().getSquareMatrix()[row][column]).getArmory().isFull()) {

                        //if the weapon deck is not empty
                        if (!this.getWeaponDeck().getUsable().isEmpty()) {
                            //fetch from deck and add to the armory
                            ((SpawnSquare) this.getMap().getSquareMatrix()[row][column]).getArmory().getWeaponList().add((Weapon) this.weaponDeck.fetch());
                        }
                    }
                }
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public KillshotTrack getTrack() {
        return track;
    }

    public void setTrack(KillshotTrack track) {
        this.track = track;
    }

    public Deck getAmmoDeck() {
        return ammoDeck;
    }

    public void setAmmoDeck(Deck ammoDeck) {
        this.ammoDeck = ammoDeck;
    }

    public Deck getPowerupDeck() {
        return powerupDeck;
    }

    public void setPowerupDeck(Deck powerupDeck) {
        this.powerupDeck = powerupDeck;
    }

    public WeaponDeck getWeaponDeck() {
        return weaponDeck;
    }

    public void setWeaponDeck(WeaponDeck weaponDeck) {
        this.weaponDeck = weaponDeck;
    }
}

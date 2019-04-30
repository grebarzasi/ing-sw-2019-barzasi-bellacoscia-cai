package it.polimi.ingsw.board;

import it.polimi.ingsw.Subject;
import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Deck;
import it.polimi.ingsw.cards.WeaponDeck;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.cards.weapon.weapon_builder.WeaponBuilder;
import it.polimi.ingsw.cards.weapon.weapon_builder.WeaponDeckBuilder;


import java.util.ArrayList;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;

public class Board extends Subject {

    private Map map;
    private KillshotTrack track;

    private Deck ammoDeck;
    private Deck powerupDeck;
    private WeaponDeck weaponDeck;

    public Board(String selection){

        this.map = new Map(selection);

        this.ammoDeck = new Deck();
        loadDeck(this.ammoDeck);



        int row;
        int column;

        for(row = 0 ; row < 3; row ++){
            for(column = 0; column < 4 ; column++){

                if(this.map.getSquareMatrix()[row][column] instanceof NonSpawnSquare){

                    NonSpawnSquare tmp = (NonSpawnSquare)this.map.getSquareMatrix()[row][column];

                    tmp.setDrop((AmmoLot)this.ammoDeck.getUsable().get(0));
                    this.ammoDeck.getDiscarded().add(this.ammoDeck.getUsable().get(0));
                    this.ammoDeck.getUsable().remove(0);

                    this.map.getSquareMatrix()[row][column] = tmp;

                }
            }
        }

        this.weaponDeck = WeaponDeckBuilder.buildDeck();
        this.weaponDeck.shuffle();

        for(row = 0 ; row < 3; row ++){
            for(column = 0; column < 4 ; column++){

                if(this.map.getSquareMatrix()[row][column] instanceof SpawnSquare){

                    SpawnSquare tmp = (SpawnSquare)this.map.getSquareMatrix()[row][column];

                    tmp.setArmory(new ArrayList<>());

                    while(tmp.getArmory().size()<3) {
                        tmp.getArmory().add((Weapon) this.weaponDeck.getUsable().get(0));
                        this.weaponDeck.getDiscarded().add(this.weaponDeck.getUsable().get(0));
                        this.weaponDeck.getUsable().remove(0);

                    }

                    this.map.getSquareMatrix()[row][column] = tmp;



                }
            }
        }



    }


    public void reachable() {
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

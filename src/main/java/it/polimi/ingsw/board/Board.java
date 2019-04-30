package it.polimi.ingsw.board;

import it.polimi.ingsw.Subject;
import it.polimi.ingsw.cards.AmmoLot;
import it.polimi.ingsw.cards.Deck;


import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;

public class Board extends Subject {

    Map map;
    KillshotTrack track;

    Deck ammoDeck;
    Deck powerupDeck;
    Deck weaponDeck;

    public void resetBoard(String selection){

        this.map = new Map(null,null);
        this.map.initiateMap(selection);

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


    }

    public Board(Map map, KillshotTrack track, Deck ammoDeck, Deck powerupDeck, Deck weaponDeck) {
        this.map = map;
        this.track = track;
        this.ammoDeck = ammoDeck;
        this.powerupDeck = powerupDeck;
        this.weaponDeck = weaponDeck;
    }

    public void reachable() {
    }


}

package it.polimi.ingsw.board;

import it.polimi.ingsw.Subject;
import it.polimi.ingsw.cards.Deck;

import static it.polimi.ingsw.cards.AmmoDeckLoader.loadDeck;

public class Board extends Subject {

    Map map;
    KillshotTrack track;

    Deck ammoDeck;
    Deck powerupDeck;
    Deck weaponDeck;

    public void resetBoard(String selection){

        this.map.initiateMap(selection);
        loadDeck(this.ammoDeck);


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

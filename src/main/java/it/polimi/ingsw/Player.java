package it.polimi.ingsw;

import it.polimi.ingsw.Board.PlayerBoard;
import it.polimi.ingsw.Board.Square;
import it.polimi.ingsw.cards.PowerUp;
import it.polimi.ingsw.cards.Weapon;

import java.util.*;

public class Player {

    private String character;

    private String username;

    private ArrayList<Token> points;

    private Square position;

    private ArrayList<Weapon> weaponsList;

    private ArrayList<PowerUp> powerupList;

    private PlayerBoard myBoard;

    private int maxActions;

    private ArrayList<Action> actions;


    public Player(String username, String character, Square position) {
        this.username = username;
        this.character = character;
        this.position = position;
    }

    public Square getPosition(){
        return position;
    }


    /**
     * Verifies whether a player can see another one
     *
     * @param p    the other player
     * @return true if the current player can see the other one; false otherwise
     */
    public boolean canSee(Player p) {
        if (getPosition().getRoom() == p.getPosition().getRoom()) {
            return true;

        } else if (getPosition().getNorth() != null && getPosition().getNorth().getRoom() == p.getPosition().getRoom()) {
            return true;

        } else if (getPosition().getEast() != null && getPosition().getEast().getRoom() == p.getPosition().getRoom()) {
            return true;

        } else if (getPosition().getWest() != null && getPosition().getWest().getRoom() == p.getPosition().getRoom()) {
            return true;

        } else if (getPosition().getSouth() != null && getPosition().getSouth().getRoom() == p.getPosition().getRoom()) {
            return true;
        }

        return false;
    }


    public void distanceTo(Square s) {

    }

    public void usePU(PowerUp pu) {

    }

    public void endTurn() {

    }

}
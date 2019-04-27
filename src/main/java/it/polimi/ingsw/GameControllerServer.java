package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Set;

public class GameControllerServer implements Controller {

    private ArrayList<Player> playerList;

    private Player currentPlayer;

    private Time timeTurn;

    public Board currentBoard;





    public GameControllerServer(ArrayList<Player> playerList, Player currentPlayer, Time timeTurn, Board currentBoard) {
        this.playerList = playerList;
        this.currentPlayer = currentPlayer;
        this.timeTurn = timeTurn;
        this.currentBoard = currentBoard;
    }


    public void setActions() {
    }

    public void getStatus(Player p) {
    }

    public void kill(Player p) {
    }

    public void finalFrenzy() {
    }

    public void finalScore(Player p) {
    }

    public void update() {
    }

    public void newTurn() {
    }

    public void endTurn() {
    }


    /**
    *ask player
     * @autor Gregorio Barzasi
     */
    public Square askPosition(){
        return null;
    }
    public Set<Player> askTarget(){
        return null;
    }
    public Weapon askWeapon(){
        return null;
    }
    public void askUseEffect(Set<Effect> eff){}




    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Time getTimeTurn() {
        return timeTurn;
    }

    public void setTimeTurn(Time timeTurn) {
        this.timeTurn = timeTurn;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }



    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }
}
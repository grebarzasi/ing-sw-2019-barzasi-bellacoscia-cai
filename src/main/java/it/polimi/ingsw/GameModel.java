package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Cell;
import it.polimi.ingsw.board.map.Room;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.socket.ClientThreadSocket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.annotation.Target;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * All the game's data are stored
 * in game model
 */

public class GameModel {

    private ArrayList<Player> playerList;

    private Player currentPlayer;

    private Terminator bot;

    private Board currentBoard;

    public GameModel(){

    }


    public GameModel(ArrayList<Player> playerList, Player currentPlayer, Board currentBoard) {
        this.playerList = playerList;
        this.currentPlayer = currentPlayer;
        this.currentBoard = currentBoard;
    }

    /**
     * Starts a model from a lobby
     * Loads the players from a lobby to the model
     * @param lobbyToStartFrom The lobby you want to start the game from
     */
    public GameModel(Lobby lobbyToStartFrom){
       for (ClientThreadSocket client : lobbyToStartFrom.getJoinedPlayers()){
           this.playerList.add(client.getOwner());
       }
        //TODO if this game wants a bot adds a bot;
    }

    public Set<Figure> askTarget(int num){
        return null;
    }
    public Weapon askWeapon(){
        return null;
    }
    public void askUseEffect(Set<Effect> eff){}

    public void setActions() {}
    public void getStatus(Figure p) {}
    public void kill(Figure p) {}
    public void finalFrenzy() {}
    public void finalScore(Figure p) {}
    public void update() {}


    /**
     * ends a turn
     * adds tokens to the killshot track
     * iterates the current player to the next
     * on the player list
     */

    public void endTurn() {

        int k;
        int i;
        int flag =0;

        for(Figure figure : this.playerList){

            if(figure.getPersonalBoard().getDamage().size() >= 11){

                for (i = 0; i < this.currentBoard.getTrack().getKillsTrack().size(); i++) {
                    if (this.currentBoard.getTrack().getKillsTrack().get(i) == null) {
                        flag = i;
                    }
                }

                this.currentBoard.getTrack().getKillsTrack().get(i).add(figure.getPersonalBoard().getDamage().get(10));
                if(figure.getPersonalBoard().getDamage().size() == 12){
                    this.currentBoard.getTrack().getKillsTrack().get(i).add(figure.getPersonalBoard().getDamage().get(11));
                }
                figure.die();
            }

        }

        this.getCurrentBoard().refillSquares();


        //iterates the current player
        if(playerList.indexOf(currentPlayer) != this.playerList.size()-1) {
            this.currentPlayer = this.playerList.get(this.playerList.indexOf(currentPlayer) + 1);
        }else{
            this.currentPlayer = this.playerList.get(1);
        }
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public Figure getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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

    public Terminator getBot() {
        return bot;
    }

    public void setBot(Terminator bot) {
        this.bot = bot;
    }



}
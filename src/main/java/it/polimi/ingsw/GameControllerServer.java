package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.Square;
import static it.polimi.ingsw.board.MapLoader.loadMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


import java.sql.Time;
import java.util.ArrayList;

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
package it.polimi.ingsw;

import it.polimi.ingsw.Board.Board;

import java.sql.Time;
import java.util.ArrayList;

public class GameControllerServer implements Controller {

    private ArrayList<Player> playerList;

    private Player currentPlayer;

    private Time timeTurn;

    public Board currentBoard;

    public GameControllerServer() {
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


    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }
}

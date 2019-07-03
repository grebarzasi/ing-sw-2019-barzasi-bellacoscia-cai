package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.controller.Controller;


import java.util.ArrayList;
import java.util.Set;

/**
 * All the game's data are stored
 * in game model
 */

public class GameModel {

    /**
     * The number of turns the game has gone into frenzy for
     */

    private int frenzyTurn = 0;

    /**
     * The list of players in the game
     */

    private ArrayList<Player> playerList=new ArrayList<>();

    /**
     * The player performing the turn
     */

    private Player currentPlayer;

    /**
     * The number of Actions that the current player cna perform
     */

    private int movesLeft;

    /**
     * True if the bot can be moved, false otherwise
     */

    private boolean hasBotAction;

    /**
     * The bot of the game if it has one
     */

    private Terminator bot;

    /**
     * The game's Board
     */

    private Board board;

    /**
     * True if the game is in frenzy mode, false otherwise
     */

    private boolean isFrenzy;

    /**
     * The turn number the game is at
     */

    private int turn;

    /**
     * The game's controller
     */

    private Controller controller;

    /**
     * empty constructor
     */
    public GameModel(){

    }

    /**
     * Default constructor
     */

    public GameModel(ArrayList<Player> playerList, String map, Controller controller) {

        this.playerList = playerList;
        this.board = new Board(map);
        this.controller = controller;
        this.currentPlayer = this.playerList.get(0);
        this.frenzyTurn = 0;
        this.movesLeft = 2;

        for (Player p : this.playerList) {
            p.setModel(this);
        }

        this.isFrenzy = false;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public boolean isFrenzy() {
        return isFrenzy;
    }

    public void setFrenzy(boolean frenzy) {
        isFrenzy = frenzy;
    }

    public boolean hasBotAction() {
        return hasBotAction;
    }

    public void setHasBotAction(boolean hasBotAction) {
        this.hasBotAction = hasBotAction;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getFrenzyState() {
        return frenzyTurn;
    }

    public void setFrenzyState(int frenzyTurn) {
        this.frenzyTurn = frenzyTurn;
    }

}
package it.polimi.ingsw;

import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.socket.ClientThreadSocket;
import it.polimi.ingsw.controller.Controller;


import java.util.ArrayList;
import java.util.Set;

/**
 * All the game's data are stored
 * in game model
 */

public class GameModel {

    private ArrayList<Player> playerList=new ArrayList<>();

    private Player currentPlayer;

    private int movesLeft;

    private boolean hasBotAction;

    private Terminator bot;

    private Board board;

    private boolean isFrenzy;

    private int turn;

    private Controller controller;

    public GameModel(){

    }

    public GameModel(ArrayList<Player> playerList, String map, Controller controller) {

        this.playerList = playerList;
        this.board = new Board(map);
        this.controller = controller;
        this.currentPlayer = this.playerList.get(0);

        for (Player p : this.playerList) {
            p.setModel(this);
        }

        this.isFrenzy = false;
    }

    /**
     * Starts a model from a lobby
     * Loads the players from a lobby to the model
     * @param lobbyToStartFrom The lobby you want to start the game from
     */

    public GameModel(Lobby lobbyToStartFrom, Controller controller) {


        for (ClientThreadSocket client : lobbyToStartFrom.getJoinedPlayers()){
           this.playerList.add(client.getOwner());
       }
        for (Player p : this.playerList) {
            p.setModel(this);
        }
        //TODO if this game wants a bot adds a bot;

        this.currentPlayer = this.playerList.get(0);
        this.isFrenzy = false;

    }

    public Set<Figure> askTarget(int num){
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

    public boolean isHasBotAction() {
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
}
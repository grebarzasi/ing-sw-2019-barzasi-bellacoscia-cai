package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.PlayerBoard;
import it.polimi.ingsw.board.Room;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.*;

public class Player {


    private Terminator termRef;

    private String username;

    private String character;

    private int points;

    private Square position;
    //needed in some weapon effect -Gregorio
    private Square oldPosition;

    private ArrayList<Weapon> weaponsList;

    private ArrayList<PowerUp> powerupList;

    private PlayerBoard personalBoard;

    private Board gameBoard;

    private GameControllerServer controllerServer;

    private int maxActions;

    private ArrayList<Action> actions;


    //for testing purposes only
    public Player(String username, String character, Square position) {
        this.username = username;
        this.character = character;
        this.position = position;
    }

    //for testing purposes only
    public Player(String username, String character, PlayerBoard personalBoard) {

        this.username = username;
        this.character = character;
        this.personalBoard = personalBoard;
    }

    public Player() {
        super();
    }

    public Square getPosition(){
        return position;
    }


    /**
     * Verifies whether a player can see another one
     *
     * @param p the player you want to know whether the current player can see or not
     * @return true if the current player can see the other one; false otherwise
     */

    public boolean canSee(Player p) {
        if (getPosition().getRoom() == p.getPosition().getRoom()) {
            return true;

        } else if (this.getPosition().getNorth() != null && getPosition().getNorth().getRoom().equals(p.getPosition().getRoom())) {
            return true;

        } else if (this.getPosition().getEast() != null && getPosition().getEast().getRoom().equals(p.getPosition().getRoom())) {
            return true;

        } else if (this.getPosition().getSouth() != null && getPosition().getSouth().getRoom().equals(p.getPosition().getRoom())){
            return true;

        } else if (this.getPosition().getWest() != null && getPosition().getWest().getRoom().equals(p.getPosition().getRoom())) {
            return true;
        }

        return false;

    }

    /**
     * Verifies whether a player can see a square
     * @param s the {@link Square} you want to know whether the current player can see or not
     * @return true is the current player can see the square; false otherwise
     */

    public boolean canSeeSquare(Square s){

        if (getPosition().getRoom() == s.getRoom()) {
            return true;

        } else if (getPosition().getNorth() != null && getPosition().getNorth().getRoom() == s.getRoom()) {
            return true;

        } else if (getPosition().getEast() != null && getPosition().getNorth().getRoom() == s.getRoom()) {
            return true;

        } else if (getPosition().getWest() != null && getPosition().getNorth().getRoom() == s.getRoom()) {
            return true;

        } else if(getPosition().getSouth() != null && getPosition().getNorth().getRoom() == s.getRoom()){
            return true;
        }

        return false;
    }

    /**
     * Verifies whether a player can see a room
     * @param r the {@link Room} you want to know whether the current player can see or not
     * @return true is the current player can see the room; false otherwise
     */

    public boolean canSeeRoom(Room r){

        if (getPosition().getRoom() == r) {
            return true;

        } else if (getPosition().getNorth() != null && getPosition().getNorth().getRoom().equals(r)) {
            return true;

        } else if (getPosition().getEast() != null && getPosition().getEast().getRoom().equals(r)) {
            return true;

        } else if (getPosition().getWest() != null && getPosition().getWest().getRoom().equals(r)) {
            return true;

        } else if(getPosition().getSouth() != null && getPosition().getSouth().getRoom().equals(r)){
            return true;

        }

        return false;


    }


    /**
     * @return a list of all players visible.
     * @author Gregorio Barzasi
     */

    public Set<Player> allCanSee(){
        Set<Player> visible = new HashSet<>(allPlayers());
        for(Player p : visible){
            if(!this.canSee(p))
                visible.remove(p);
        }
        return visible;
    }
    /**
     * @return a list of all players.
     * @author Gregorio Barzasi
     */
    public Set<Player> allPlayers(){
        return new HashSet<>(controllerServer.getPlayerList());
    }

    /**
     * Make damage and set marks
     * @author Gregorio Barzasi
     */
    public void inflictDamage(int num,Player target){
        Token t = new Token(this);
        for(int i=0; i<=num; i++) {
            target.getPersonalBoard().addDamage(t);
        }
    }
    public void inflictMark(int num,Player target){
        Token t = new Token(this);
        for(int i=0; i<=num; i++) {
            target.getPersonalBoard().addMark(t);
        }
    }


    /**
     * Calculates the distance between a player and a square
     * @param s the {@link Square} you wanna know the distance to
     * @return the distance to that {@Link Square}, -1 if unreachable:
     * meaning that the square is a blank since all maps are completely connected
     *
     * calculated using Breadth-first search
     *
     */

    public int distanceTo(Square s) {

        int distance = 0;
        int size;
        int i=0;
        LinkedList<Square> next = new LinkedList<>();
        HashSet<Square> visited = new HashSet<>();
        next.add(this.getPosition());

        while(!next.isEmpty()) {


            size = next.size();

            for (i=0 ;i < size; i++){
                Square node = next.getFirst();
                next.pop();


                if (node == s) {
                    return distance;
                }

                if (node.getNorth() != null && !visited.contains(node.getNorth())) {
                    next.add(node.getNorth());
                }
                if (node.getEast() != null && !visited.contains(node.getEast())) {
                    next.add(node.getEast());
                }
                if (node.getSouth() != null && !visited.contains(node.getSouth())) {
                    next.add(node.getSouth());
                }
                if (node.getWest() != null && !visited.contains(node.getWest())) {
                    next.add(node.getWest());
                }

                visited.add(node);
            }

            distance++;
        }

        return -1;

    }

    public void getPoints(int i){
        this.points += i;
    }

    public void usePU(PowerUp pu) {

    }

    public void endTurn() {

    }

    // needed in some Power-Up effect -Carlo
    public void doAction(Action name){

    }



    public Terminator getTermRef() {
        return termRef;
    }

    public void setTermRef(Terminator termRef) {
        this.termRef = termRef;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public ArrayList<Weapon> getWeaponsList() {
        return weaponsList;
    }

    public void setWeaponsList(ArrayList<Weapon> weaponsList) {
        this.weaponsList = weaponsList;
    }

    public ArrayList<PowerUp> getPowerupList() {
        return powerupList;
    }

    public void setPowerupList(ArrayList<PowerUp> powerupList) {
        this.powerupList = powerupList;
    }

    public PlayerBoard getPersonalBoard() {
        return personalBoard;
    }

    public void setPersonalBoard(PlayerBoard personalBoard) {
        this.personalBoard = personalBoard;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameControllerServer getControllerServer() {
        return controllerServer;
    }

    public void setControllerServer(GameControllerServer controllerServer) {
        this.controllerServer = controllerServer;
    }

    public int getMaxActions() {
        return maxActions;
    }

    public void setMaxActions(int maxActions) {
        this.maxActions = maxActions;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

}
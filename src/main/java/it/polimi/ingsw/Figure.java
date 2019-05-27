package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Room;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.controller.Controller;

import java.util.*;


/**
 * A figure of any kind in the game
 *
 * @author Yuting Cai
 * Unless method is otherwise stated
 */

public class Figure {

    private static final int width = 4;
    private static final int height = 3;
    private static final int range = 3;


    //chosen character

    private String character;

    //the score of the player

    private int points;

    //the square to which the figure belongs

    private Square position;

    //needed in some weapon effect

    private Square oldPosition;

    //The Playes's Playerboard

    private PlayerBoard personalBoard;

    private Board gameBoard;

    private GameModel model;

    private int maxActions;

    private ArrayList<Action> actions;

    public ArrayList<Square> canGo() {

        int row;
        int column;

        ArrayList<Square> destinations = new ArrayList<>();

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {
                if (this.distanceTo(this.model.getCurrentBoard().getMap().getSquareMatrix()[row][column]) <= 3) {

                    destinations.add(this.model.getCurrentBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        return destinations;
    }


    public Figure(String character){

        this.character = character;
        this.points = 0;
        this.position = null;
        this.oldPosition = null;
        this.personalBoard = new PlayerBoard(this);
        this.actions = new ArrayList<>();

    }

    //for testing purposes only
    public Figure(String character, Square position) {
        this.character = character;
        this.position = position;
    }

    //for testing purposes only
    public Figure(String character, PlayerBoard personalBoard) {
        this.character = character;
        this.personalBoard = personalBoard;
    }

    public Figure() {
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

    public boolean canSee(Figure p) {
        if (getPosition().getRoom() == p.getPosition().getRoom()) {
            return true;

        } else if (this.getPosition().getNorth() != null && getPosition().getNorth().getRoom().equals(p.getPosition().getRoom())) {
            return true;

        } else if (this.getPosition().getEast() != null && getPosition().getEast().getRoom().equals(p.getPosition().getRoom())) {
            return true;

        } else if (this.getPosition().getSouth() != null && getPosition().getSouth().getRoom().equals(p.getPosition().getRoom())){
            return true;

        } else
            return this.getPosition().getWest() != null && getPosition().getWest().getRoom().equals(p.getPosition().getRoom());
        //intellij simplification return false if last condition is not verified
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

        } else return getPosition().getSouth() != null && getPosition().getNorth().getRoom() == s.getRoom();
        //intellij simplification return false if last condition is not verified
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

        } else return getPosition().getSouth() != null && getPosition().getSouth().getRoom().equals(r);
        //intellij simplification return false if last condition is not verified

    }


    /**
     * @return a list of all players visible.
     * @author Gregorio Barzasi
     */

    public Set<Figure> allCanSee(){
        Set<Figure> visible = new HashSet<>();

        for(Figure p : this.allFigures()){
            if(this.canSee(p))
                visible.add(p);
        }
        return visible;
    }
    /**
     * @return a list of all players.
     * @author Gregorio Barzasi
     */
    public Set<Figure> allFigures(){
        Set<Figure> f = new HashSet<>(model.getPlayerList());
        if(model.getBot()!=null)
         f.add(model.getBot());
        return f;
    }

    /**
     * Make damage and set marks
     * @author Gregorio Barzasi
     */

    //if you start from 0 you use < mate.  :)

    public void inflictDamage(int num, Figure target){
        Token t = new Token(this);
        for(int i=0; i<num; i++) {
            target.getPersonalBoard().addDamage(t);
        }
    }
    public void inflictMark(int num, Figure target){
        Token t = new Token(this);
        for(int i=0; i<num; i++) {
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
     * @author Yuting Cai
     */

    public int distanceTo(Square s) {

        int distance = 0;
        int size;
        int i;
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

    /**
     * RIP
     * Manages player death, should be invoked and the end of the turn in which the player is killed
     * distributes points to the players who contributed to this player's death
     * re-dimensions this players's point vector
     * resets this player's damage array
     */

    public void die(){

        HashMap<Figure,Integer> contributors = new HashMap<>();
        ArrayList<Figure> murderers = new ArrayList<>();
        int tmp;
        int i;

        //give point to the player who inflicted first blood
        this.getPersonalBoard().getDamage().get(0).getOwner().addPoints(1);
        System.out.println("added first blood damage");

        //maps each player with their contribution to the list
        for (Token t : this.getPersonalBoard().getDamage()){

            if(!contributors.containsKey(t.getOwner())) {
                contributors.put(t.getOwner(),1);
            }else{
                tmp = contributors.get(t.getOwner());
                contributors.replace(t.getOwner(),tmp+1);
            }
        }

        //loads all players who have contributed to the kill into an Arraylist
        for(Figure f: contributors.keySet()){
            murderers.add(f);
        }

        System.out.println("number of murderers: " + murderers.size());

        //initialising a new arraylist of ordered contributors
        ArrayList<Figure> ordered = new ArrayList<>();
        boolean added = false;
        //inserts in order into a new list


        //sorted insertion into ordered
        while(!murderers.isEmpty()){

            System.out.print("unchcked murderers: " + murderers.size());

            added = false;

            for(i=0;i<ordered.size();i++) {

                if (contributors.get(ordered.get(i)) == contributors.get(murderers.get(0))) {
                    ordered.add(i, murderers.get(0));
                    murderers.remove(0);
                    System.out.print("added in the middle\n");
                    added = true;
                    break;
                }
                if (!added && contributors.get(ordered.get(i)) > contributors.get(murderers.get(0))) {
                    ordered.add(i, murderers.get(0));
                    murderers.remove(0);
                    System.out.print("added in the end\n");
                    added = true;
                    break;
                }
            }

            if(!added) {
                ordered.add(murderers.get(0));
                murderers.remove(0);
                System.out.print("added in the end\n");
            }

        }

        System.out.print(ordered.get(0).getCharacter()+ "\n");

        Collections.reverse(ordered);

        System.out.print(ordered.get(0).getCharacter()+ "\n");

        int k;

        //in case of same damage dealt by multiple players the one who hit firs get sorted in front
        for(i=0;i<ordered.size();i++) {
            for (k = i; k < ordered.size(); k++) {
                if (contributors.get(ordered.get(k)).equals(contributors.get(ordered.get(i))) && damagePriority(ordered.get(k)) < damagePriority(ordered.get(i))) {

                    Figure temp = ordered.get(i);
                    ordered.set(i,ordered.get(k));
                    ordered.set(k,temp);

                }
            }
        }



        ordered.set(1,ordered.get(1));

        System.out.print("out of while");

        System.out.println("Ordered List Size: " + ordered.size());

        System.out.print(ordered.get(0).getCharacter()+ "\n");

        for( i=0 ; i<ordered.size() ; i++) {

            System.out.print("Added points to "+ i +": " + this.getPersonalBoard().getPointVec()[i] + "\n");

            ordered.get(i).addPoints(this.getPersonalBoard().getPointVec()[i]);

            System.out.print(ordered.get(i).getCharacter() + "Has "+ ordered.get(i).getPoints() + " points \n");

        }

        ordered.set(1,ordered.get(1));

        this.getPersonalBoard().resetDamage();
        this.getPersonalBoard().addSkull();

    }

    /**
     * private method used in die() for determining scoring
     * priority inc ase 2 player deal the same damage
     * @param f1 Figure to check if this has priority over
     * @return true if this has priority, false otherwise
     */

    private int damagePriority(Figure f1){

        int i;
        for(i=0;i<this.getPersonalBoard().getDamage().size();i++){
            if(this.getPersonalBoard().getDamage().get(i).getOwner().equals(f1)){
                return i;
            }
        }

        return -1;
    }


    public int getPoints() {
        return points;
    }

    public void addPoints(int addedPoints){
        this.points += addedPoints;
    }

    public void usePU(PowerUp pu) {

    }

    /*just a bunch of setters and getters*/

    public Square getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Square oldPosition) {
        this.oldPosition = oldPosition;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPosition(Square position) {
        this.position = position;
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

    public GameModel getModel() {
        return model;
    }

    public void setModel(GameModel model) {
        this.model = model;
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
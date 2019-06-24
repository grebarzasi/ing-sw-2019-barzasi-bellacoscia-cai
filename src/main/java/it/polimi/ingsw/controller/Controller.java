package it.polimi.ingsw.controller;

import it.polimi.ingsw.*;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Room;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.connection.ClientHandler;

import java.util.ArrayList;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * Main controller class, implemented with satate pattern.
 *
 * @author Yuting Cai
 */

public class Controller {

    private static String[] ALL_CHARACTERS = {"red","blue","yellow","green","grey"};
    private static final int[] frenzyPointsVec;

    static {
        frenzyPointsVec = new int[]{2, 1, 1, 1, 0};
    }




    private static final int DEATH_DAMAGE = 11;
    private static final int OVER_KILL_DAMAGE = 12;


    //State pattern states

    ControllerState asBot;
    ControllerState choosingMove;
    ControllerState choosingPowerUpToUse;
    ControllerState choosingWeapon;
    ControllerState discardingPowerUp;
    ControllerState frenzySpecialAction;
    ControllerState moving;
    ControllerState picking;
    ControllerState pickingWeapon;
    ControllerState reloading;
    ControllerState shooting;
    ControllerState spawning;
    ControllerState teleporting;
    ControllerState usingNewton;
    ControllerState endGame;


    /**
     * Current controller state
     */

    ControllerState currentState;


    /**
     * True if the game has frenzy mode, false otherwise
     */
    private boolean hasFrenzy;

    /**
     * True if the game has bot, false otherwise
     */
    private boolean hasBot;


    /**
     * Main model of the controller, sends update based on this
     */
    private GameModel model;


    /**
     * View interface to communicate with user
     */
    private View view;

    /**
     * Notifier
     */
    private UpdateBuilder marshal;


    /**
     * Starts the game reading parameters from a lobby
     * @param lobby Lobby to start from
     */

    public Controller(Lobby lobby) {

        lobby.setController(this);
        this.marshal=new UpdateBuilder(this);

        ArrayList<Player> playerList = new ArrayList<>();

        for(ClientHandler s: lobby.getJoinedPlayers()){
            playerList.add(s.getOwner());
        }

        this.view=playerList.get(0).getView();
        String map = intToMap(lobby.getMapPref());



        this.model = new GameModel(playerList,map, this);

        this.hasFrenzy = lobby.hasFinalFrenzy();
        this.hasBot = lobby.hasTerminatorPref();


        if(this.hasBot){

            String botColor = firstAvailableColor(playerList);
            this.model.setBot(new Terminator(botColor,model));

        }

        this.getModel().getBoard().getTrack().setSkullMax(lobby.getKillPref());


        this.asBot = new AsBot(this);
        this.choosingMove = new ChoosingMove(this);
        this.choosingPowerUpToUse = new ChoosingPowerUp(this);
        this.discardingPowerUp = new DiscardingPowerUp(this);
        this.choosingWeapon = new ChoosingWeapon(this);
        this.moving = new Moving(this);
        this.picking = new Picking(this);
        this.pickingWeapon = new PickingWeapon(this);
        this.reloading = new Reloading(this);
        this.shooting = new Shooting(this);
        this.teleporting = new Teleporting(this);
        this.usingNewton = new UsingNewton(this);
        this.shooting = new Shooting(this);
        this.spawning = new Spawning(this);
        this.frenzySpecialAction = new FrenzySpecialAction(this);
        this.endGame = new EndGame(this);

    }

    /**
     * Only for testing prurposes
     * @param playerList the list of players to start from
     */


    public Controller(ArrayList<Player> playerList){

        this.marshal=new UpdateBuilder(this);

        String map = intToMap(3);



        this.model = new GameModel(playerList,map, this);

        this.hasFrenzy = true;
        this.hasBot = true;

        //noinspection ConstantConditions
        if(this.hasBot){

            String botColor = firstAvailableColor(playerList);
            this.model.setBot(new Terminator(botColor,model));

        }

        this.getModel().getBoard().getTrack().setSkullMax(8);


        this.asBot = new AsBot(this);
        this.choosingMove = new ChoosingMove(this);
        this.choosingPowerUpToUse = new ChoosingPowerUp(this);
        this.discardingPowerUp = new DiscardingPowerUp(this);
        this.choosingWeapon = new ChoosingWeapon(this);
        this.moving = new Moving(this);
        this.picking = new Picking(this);
        this.pickingWeapon = new PickingWeapon(this);
        this.reloading = new Reloading(this);
        this.shooting = new Shooting(this);
        this.teleporting = new Teleporting(this);
        this.usingNewton = new UsingNewton(this);
        this.shooting = new Shooting(this);
        this.spawning = new Spawning(this);
        this.frenzySpecialAction = new FrenzySpecialAction(this);
        this.endGame = new EndGame(this);

    }

    /**
     * Used to determine the first available figure to use as Bot
     * @param playerList the list of all players
     * @return the first available figure
     */

    private static String firstAvailableColor(ArrayList<Player> playerList){

        //marks the character names that have been taken already
        //the characters are stored in a pre defined array
        int i;
        for(i = 0 ; i < ALL_CHARACTERS.length ; i++){
            for (Player p : playerList) {
                if (p.getCharacter().equals(ALL_CHARACTERS[i])){
                    ALL_CHARACTERS[i] = "taken";
                }
            }
        }

        //returns the first unmarked character
        for(i = 0 ; i < ALL_CHARACTERS.length ; i++){
            if(!ALL_CHARACTERS[i].equals("taken")){
                return ALL_CHARACTERS[i];
            }
        }

        return "red";
    }

    /**
     * Goes back to choosing the move
     */

    public void goBack(){
        this.currentState = this.choosingMove;
        this.currentState.executeState();
    }

    public void decreaseMoveLeft(){
        this.setMovesLeft(this.model.getMovesLeft() -1 );
    }

    /**
     * ends a turn
     * adds tokens to the killshot track
     * iterates the current player to the next
     * on the player list
     */

    void endTurn() {

        for (Figure f : this.model.getPlayerList()) {

            if (f.getPersonalBoard().getDamage().size() >= DEATH_DAMAGE) {

                ArrayList<Token> addToTrack = new ArrayList<>();
                addToTrack.add(f.getPersonalBoard().getDamage().get(DEATH_DAMAGE - 1));
                if(f.getPersonalBoard().getDamage().size() >= OVER_KILL_DAMAGE) {
                    addToTrack.add(f.getPersonalBoard().getDamage().get(OVER_KILL_DAMAGE - 1));
                }
                this.getBoard().getTrack().getKillsTrack().add(addToTrack);

                f.die();
                f.setPosition(null);

                this.checkEndStatus();

            }

        }


        if(this.hasBot && this.getModel().getBot().isDead() && this.model.getTurn()!=0){
            this.getModel().getBot().setPosition(this.returnSpawns().get((int) (Math.random() * returnSpawns().size())));
            this.getModel().getBot().setDead(false);
        }

        this.model.getBoard().refillSquares();

        this.checkEndStatus();
        this.iteratePlayer();
        this.resetTurn();

    }

    /**
     * Private method for checking if a the game should end used in end turn
     */

    private void checkEndStatus(){

        if(!checkLeftPlayer())
            endGame();

        if(this.getBoard().getTrack().getKillsTrack().size() == this.getBoard().getTrack().getSkullMax()){

            if(this.hasFrenzy){

                this.startFrenzy();
                this.getCurrentPlayer().setStartedFrenzy(true);
                this.model.getBoard().refillSquares();

                if(this.getCurrentPlayer().getStartedFrenzy()){
                    this.decreaseMoveLeft();
                }

                this.model.setFrenzyState(this.model.getFrenzyState() + 1);

                if(this.model.getFrenzyState() == this.model.getPlayerList().size()){
                    /* ****************GAME_ENDS******************* */
                    this.endGame();
                    /* ****************GAME_ENDS******************* */
                }


            }else
                //noinspection ConstantConditions
                if(!this.hasFrenzy){
                /* ****************GAME_ENDS******************* */
                this.endGame();
                /* ****************GAME_ENDS******************* */
            }

        }
    }

    /**
     * Iterates the current player to the next one
     */

    void iteratePlayer(){

        if (this.model.getPlayerList().indexOf(this.model.getCurrentPlayer()) != this.model.getPlayerList().size() - 1) {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(this.model.getPlayerList().indexOf(model.getCurrentPlayer()) + 1));
        } else {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(0));
        }

        if(!checkLeftPlayer())
            endGame();

        //if disconnected or inactive skip turn
        if(model.getCurrentPlayer().isDisconnected()||model.getCurrentPlayer().isInactive()) {
//            endTurn();
        }


    }

    /**
     * Restes the parameters of the turn
     * Resets the current state to choosing move
     * and shifts the view listened by the server
     */

    private void resetTurn(){

        this.model.setMovesLeft(2);
        this.model.setHasBotAction(true);
        this.model.setTurn(this.model.getTurn() + 1);
        this.view = this.getCurrentPlayer().getView();
        this.update();
        this.goBack();

    }

    /**
     * ends the game by displaying the leaderboard
     */

    private void endGame(){

        this.getModel().getBoard().getTrack().getPoints();
        this.update();

        this.setCurrentState(endGame);
        this.currentState.executeState();

        if(this.hasFrenzy){
            for(Player p: this.model.getPlayerList()){
                p.die();
            }
        }

    }

    /**
     * Starts frenzy mode
     */

    private void startFrenzy(){
        this.model.setFrenzy(true);

        for(Player p : this.model.getPlayerList()) {
            if (p.getPersonalBoard().canFlip()) {
                p.getPersonalBoard().setPointVec(frenzyPointsVec);
            }
        }
    }


    void askVenoms(Set<Figure> targets, Figure hitter){

        if(this.hasBot()) {
            targets.remove(this.getModel().getBot());
        }

        ArrayList<Player> finalTargets = new ArrayList<>();

        for(Figure p : targets){
            finalTargets.add((Player)p);
        }

        Player tmp = this.getCurrentPlayer();

        int i;

        for(i = 0 ; i < finalTargets.size() ; i++){

            ArrayList<PowerUp> filtered = new ArrayList<>(finalTargets.get(i).getPowerupList());
            Controller.filterPUs(filtered,PowerUp.TAGBACK_GRENADE);

            if(filtered.isEmpty()){
                finalTargets.remove(i);
            }
        }

        for(Player p : finalTargets){

            this.getModel().setCurrentPlayer(p);
            this.setView(this.getCurrentPlayer().getView());

            boolean useTagback = this.getView().showBoolean(ControllerMessages.ASKING_VENOM);

            if(useTagback){

                ArrayList<PowerUp> options = new ArrayList<>(p.getPowerupList());

                Controller.filterPUs(options,PowerUp.TAGBACK_GRENADE);

                PowerUp choice = this.getView().showPowerUp(options);


                if(choice != null){
                    p.inflictMark(1,hitter);
                    p.removePowerUp(choice);
                }

            }

        }

        this.getModel().setCurrentPlayer(tmp);
        this.setView(tmp.getView());
        //y don need this. the caller does it;
//        this.setCurrentState(choosingMove);
        this.update();

    }

    /**
     * Determines the squares a player can reach within a given range
     *
     * @param p the Player inquiring on
     * @param range the range
     * @return the list of possible squares
     */

    ArrayList<Square> canGo(Figure p, int range){

        int row;
        int column;

        ArrayList<Square> options = new ArrayList<>();

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH; column++){

                if(p.distanceTo(this.getModel().getBoard().getMap().getSquareMatrix()[row][column])
                        <= range && ! this.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor().equals(Room.VOID)){

                    options.add(this.getBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        return options;

    }


    /**
     * Updates the view of each player
     */

    public void update(){
        String s = marshal.create().toString();
        for(Player p: model.getPlayerList())
                p.getView().sendsUpdate(s);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts the map preference from integer to the
     * name of the map
     * @param i the preference expressed in int
     * @return the corresponding name
     */
    private String intToMap(int i){

        String map = "large";

        switch(i){

            case 4:
                map = "small";
                break;
            case 3:
                map = "medium2";
                break;
            case 2:
                map = "medium1";
                break;
            case 1:
                map = "large";
                break;

        }

        return map;
    }

    /**
     * Static method that removes all Power Ups NOT of the given type
     * from an ArrayList of Power Ups
     * @param puList ArrayList to remove from
     * @param toKeep All Power Ups to remove excluding this one
     */
    static void filterPUs(ArrayList<PowerUp> puList , String toKeep){

        puList.removeIf(p -> !p.getName().equals(toKeep));

    }

    private boolean checkLeftPlayer() {
        for(Player p: model.getPlayerList())
            if(!p.isDisconnected()&&!p.isInactive())
                return true;
        return false;
    }

    void checkInactivity(){
        if(this.getView().isInactive() || this.getView().isDisconnected()){
            this.endTurn();
        }
    }

    ArrayList<Square> returnSpawns(){

        ArrayList<Square> spawns = new ArrayList<>();
        int row;
        int column;

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH; column ++){
                if(this.getBoard().getMap().getSquareMatrix()[row][column].isSpawn())
                    spawns.add(this.getBoard().getMap().getSquareMatrix()[row][column]);
            }
        }

        return spawns;

    }


    ///////////////////////
    //Getters and Setters//
    ///////////////////////


    public Board getBoard() {
        return this.getModel().getBoard();
    }

    ControllerState getPickingWeapon() {
        return pickingWeapon;
    }

    ControllerState getShooting() {
        return shooting;
    }

    public ControllerState getSpawning() {
        return spawning;
    }

    public ControllerState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ControllerState currentState) {
        this.currentState = currentState;
    }

    public GameModel getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    private void setMovesLeft(int moves) {
        this.model.setMovesLeft(moves);
    }

    public Player getCurrentPlayer() {
        return this.model.getCurrentPlayer();
    }

    public boolean hasBot() {
        return hasBot;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setHasBot(boolean hasBot) {
        this.hasBot = hasBot;
    }
}

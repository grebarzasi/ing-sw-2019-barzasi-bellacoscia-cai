package it.polimi.ingsw.controller;

import it.polimi.ingsw.*;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.connection.ClientHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main controller class, implemented with satate pattern.
 *
 * @author Yuting Cai
 */

public class Controller {

    private static String[] ALL_CHARACTERS = {"red","blue","yellow","green","gray"};
    private static final int[] frenzyPointsVec;

    static {
        frenzyPointsVec = new int[]{2, 1, 1, 1};
    }

    private static final int HEIGHT = 3;
    private static final int WIDTH = 4;

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

    }


    public Controller(ArrayList<Player> playerList){

        this.marshal=new UpdateBuilder(this);

        String map = intToMap(3);



        this.model = new GameModel(playerList,map, this);

        this.hasFrenzy = true;
        this.hasBot = true;


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

    }

    private static String firstAvailableColor(ArrayList<Player> playerList){

        int i;
        for(i = 0 ; i < ALL_CHARACTERS.length ; i++){
            for (Player p : playerList) {
                if (p.getCharacter().equals(ALL_CHARACTERS[i])){
                    ALL_CHARACTERS[i] = "taken";
                }
            }
        }

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
        this.currentState.command();
    }

    public void decreaseMoveLeft(){
        this.setMovesLeft(this.getMovesLeft() -1 );
    }

    /**
     * ends a turn
     * adds tokens to the killshot track
     * iterates the current player to the next
     * on the player list
     */

    public void endTurn() {

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

        this.model.getBoard().refillSquares();


        this.iteratePlayer();
        this.resetTurn();

    }

    private void checkEndStatus(){

        if(this.getBoard().getTrack().getKillsTrack().size() == this.getBoard().getTrack().getSkullMax()){

            if(this.hasFrenzy){

                this.startFrenzy();
                this.getCurrentPlayer().setStartedFrenzy(true);
                this.model.getBoard().refillSquares();

                this.goBack();
                this.model.setFrenzyState(this.model.getFrenzyState() + 1);

                if(this.model.getFrenzyState() == this.model.getPlayerList().size()){
                    /* ****************GAME_ENDS******************* */
                    this.endGame();
                    /* ****************GAME_ENDS******************* */
                }


            }else if(!this.hasFrenzy){
                /* ****************GAME_ENDS******************* */
                this.endGame();
                /* ****************GAME_ENDS******************* */
            }

        }
    }

    void iteratePlayer(){

        if (this.model.getPlayerList().indexOf(this.model.getCurrentPlayer()) != this.model.getPlayerList().size() - 1) {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(this.model.getPlayerList().indexOf(model.getCurrentPlayer()) + 1));
        } else {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(0));
        }

        //if disconnected or inactive skip turn
        if(model.getCurrentPlayer().isDisconnected()||model.getCurrentPlayer().isInactive()) {
            if(checkLeftPlayer())
                iteratePlayer();
            endGame();
        }


    }

    private void resetTurn(){

        this.model.setMovesLeft(2);
        this.model.setHasBotAction(true);
        this.model.setTurn(this.model.getTurn() + 1);
        this.view = this.getCurrentPlayer().getView();
        this.update();
        this.goBack();

    }

    private void endGame(){

        this.view.displayLeaderboard();

    }

    private void startFrenzy(){
        this.model.setFrenzy(true);
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

        for(row = 0; row < HEIGHT; row++){
            for(column = 0; column < WIDTH; column++){

                if(p.distanceTo(this.getModel().getBoard().getMap().getSquareMatrix()[row][column])
                        <= range && ! this.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor().equals("black")){

                    options.add(this.getBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        return options;

    }

    public void update() {
        String s = marshal.create().toString();
        for(Player p: model.getPlayerList())
            if(!p.isDisconnected())
                p.getView().sendsUpdate(s);
    }

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

    static void filterPUs(ArrayList<PowerUp> puList , String toKeep){

        int i;
        for(i = 0 ; i < puList.size() ; i++ ){
            if(!puList.get(i).getName().equals(toKeep)){
                puList.remove(i);
            }
        }

    }

    public void timeOut(){
        this.goBack();
        this.decreaseMoveLeft();
    }

    public void finalTimeOut() {
        this.goBack();
        this.endTurn();
    }

    public Board getBoard() {
        return this.getModel().getBoard();
    }

    public ControllerState getAsBot() {
        return asBot;
    }

    public void setAsBot(ControllerState asBot) {
        this.asBot = asBot;
    }

    public ControllerState getChoosingMove() {
        return choosingMove;
    }

    public void setChoosingMove(ControllerState choosingMove) {
        this.choosingMove = choosingMove;
    }

    public ControllerState getChoosingPowerUpToUse() {
        return choosingPowerUpToUse;
    }

    public void setChoosingPowerUpToUse(ControllerState choosingPowerUpToUse) {
        this.choosingPowerUpToUse = choosingPowerUpToUse;
    }

    public ControllerState getChoosingWeapon() {
        return choosingWeapon;
    }

    public void setChoosingWeapon(ControllerState choosingWeapon) {
        this.choosingWeapon = choosingWeapon;
    }

    public ControllerState getDiscardingPowerUp() {
        return discardingPowerUp;
    }

    public void setDiscardingPowerUp(ControllerState discardingPowerUp) {
        this.discardingPowerUp = discardingPowerUp;
    }

    public ControllerState getMoving() {
        return moving;
    }

    public void setMoving(ControllerState moving) {
        this.moving = moving;
    }

    public ControllerState getPicking() {
        return picking;
    }

    public void setPicking(ControllerState picking) {
        this.picking = picking;
    }

    public ControllerState getPickingWeapon() {
        return pickingWeapon;
    }

    public void setPickingWeapon(ControllerState pickingWeapon) {
        this.pickingWeapon = pickingWeapon;
    }

    public ControllerState getReloading() {
        return reloading;
    }

    public void setReloading(ControllerState reloading) {
        this.reloading = reloading;
    }

    public ControllerState getShooting() {
        return shooting;
    }

    public void setShooting(ControllerState shooting) {
        this.shooting = shooting;
    }

    public ControllerState getSpawning() {
        return spawning;
    }

    public void setSpawning(ControllerState spawning) {
        this.spawning = spawning;
    }

    public ControllerState getTeleporting() {
        return teleporting;
    }

    public void setTeleporting(ControllerState teleporting) {
        this.teleporting = teleporting;
    }

    public ControllerState getUsingNewton() {
        return usingNewton;
    }

    public void setUsingNewton(ControllerState usingNewton) {
        this.usingNewton = usingNewton;
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

    public void setModel(GameModel model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public int getMovesLeft() {
        return this.getModel().getMovesLeft();
    }

    public void setMovesLeft(int moves) {
        this.model.setMovesLeft(moves);
    }

    public Player getCurrentPlayer() {
        return this.model.getCurrentPlayer();
    }

    public static int[] getFrenzyPointsVec() {
        return frenzyPointsVec;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getDeathDamage() {
        return DEATH_DAMAGE;
    }

    public static int getOverKillDamage() {
        return OVER_KILL_DAMAGE;
    }

    public ControllerState getFrenzySpecialAction() {
        return frenzySpecialAction;
    }

    public void setFrenzySpecialAction(ControllerState frenzySpecialAction) {
        this.frenzySpecialAction = frenzySpecialAction;
    }

    public boolean isHasFrenzy() {
        return hasFrenzy;
    }

    public void setHasFrenzy(boolean hasFrenzy) {
        this.hasFrenzy = hasFrenzy;
    }

    public boolean hasBot() {
        return hasBot;
    }

    public void setHasBot(boolean hasBot) {
        this.hasBot = hasBot;
    }

    public void setView(View view) {
        this.view = view;
    }

    public UpdateBuilder getMarshal() {
        return marshal;
    }

    public void setMarshal(UpdateBuilder marshal) {
        this.marshal = marshal;
    }

    public boolean checkLeftPlayer() {
        for(Player p: model.getPlayerList())
            if(!p.isDisconnected()&&!p.isInactive())
                return true;
            return false;
    }




}

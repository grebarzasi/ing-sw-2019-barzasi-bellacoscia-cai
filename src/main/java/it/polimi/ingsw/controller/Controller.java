package it.polimi.ingsw.controller;

import it.polimi.ingsw.*;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.connection.socket.SClientHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main controller class, implemented with satate pattern.
 *
 * @author Yuting Cai
 */

public class Controller {

    private static final int[] frenzyPointsVec = {2,1,1,1};

    private static final int height = 3;
    private static final int width = 4;

    private static final int deathDamage = 11;
    private static final int overKillDamage = 12;


    //State pattern states

    ControllerState starting;
    ControllerState asBot;
    ControllerState choosingMove;
    ControllerState choosingPowerUpToUse;
    ControllerState choosingWeapon;
    ControllerState discardingPowerUp;
    ControllerState discardingWeapon;
    ControllerState moving;
    ControllerState picking;
    ControllerState pickingWeapon;
    ControllerState reloading;
    ControllerState shooting;
    ControllerState teleporting;
    ControllerState usingNewton;
    ControllerState spawning;
    ControllerState frenzySpecialAction;


    //current state patten state

    ControllerState currentState;


    //the game has frenzy
    private boolean hasFrenzy;
    //the game has bot
    private boolean hasBot;


    //Model
    private GameModel model;

    //View interface
    private View view;

    //notifier
    private UpdateBuilder marshal;




    //Starts the game from a lobby

    public Controller(Lobby lobby) {
        this.marshal=new UpdateBuilder(this);
        ArrayList<Player> playerList = new ArrayList<>();

        for(SClientHandler s: lobby.getJoinedPlayers()){
            playerList.add(s.getOwner());
        }

        this.view=playerList.get(0).getView();
        String map = intToMap(lobby.getMapPref());



        this.model = new GameModel(playerList,map, this);

        this.hasFrenzy = lobby.hasFinalFrenzy();
        this.hasBot = lobby.hasTerminatorPref();
        this.getModel().getBoard().getTrack().setSkullMax(lobby.getKillPref());

        this.asBot = new AsBot(this);
        this.choosingMove = new ChoosingMove(this);
        this.choosingPowerUpToUse = new ChoosingPowerUpToUse(this);
        this.choosingWeapon = new ChoosingWeapon(this);
        this.moving = new Moving(this);
        this.picking = new Picking(this);
        this.pickingWeapon = new PickingWeapon(this);
        this.reloading = new Reloading(this);
        this.shooting = new Shooting(this);
        this.teleporting = new Teleporting(this);
        this.usingNewton = new UsingNewton(this);
        this.shooting = new Spawning(this);
        this.spawning = new Spawning(this);
        this.frenzySpecialAction = new FrenzySpecialAction(this);

    }

    public Controller(Lobby lobby, View view) {

        this.model = new GameModel(lobby, this);
        this.view = view;

        this.asBot = new AsBot(this);
        this.choosingMove = new ChoosingMove(this);
        this.choosingPowerUpToUse = new ChoosingPowerUpToUse(this);
        this.choosingWeapon = new ChoosingWeapon(this);
        this.moving = new Moving(this);
        this.picking = new Picking(this);
        this.pickingWeapon = new PickingWeapon(this);
        this.reloading = new Reloading(this);
        this.shooting = new Shooting(this);
        this.teleporting = new Teleporting(this);
        this.usingNewton = new UsingNewton(this);
        this.shooting = new Spawning(this);
        this.spawning = new Spawning(this);
        this.frenzySpecialAction = new FrenzySpecialAction(this);

    }

    /**
     * Goes back to choosing the move
     */

    public void goBack(){
        this.setCurrentState(this.choosingMove);
        this.choosingMove.command();
    }

    public void dereaseMoveLeft(){
        this.setMovesLeft(this.getMovesLeft() -1 );
    }

    /**
     * ends a turn
     * adds tokens to the killshot track
     * iterates the current player to the next
     * on the player list
     */

    public void endTurn() {

        int k;
        int i;
        int flag = 0;

        for (Figure f : this.model.getPlayerList()) {

            if (f.getPersonalBoard().getDamage().size() >= deathDamage) {

                ArrayList<Token> addToTrack = new ArrayList<>();
                addToTrack.add(f.getPersonalBoard().getDamage().get(deathDamage - 1));
                addToTrack.add(f.getPersonalBoard().getDamage().get(overKillDamage -1));
                this.getBoard().getTrack().getKillsTrack().add(addToTrack);

                if(this.getBoard().getTrack().getKillsTrack().size() == this.getBoard().getTrack().getSkullMax()){

                    if(this.hasFrenzy){

                        this.startFrenzy();
                        this.getCurrentPlayer().setStartedFrenzy(true);
                        this.model.getBoard().refillSquares();
                        if(this.model.getFrenzyState() == this.model.getPlayerList().size()){
                            /*****************GAME_ENDS********************/
                            this.endGame();
                            /*****************GAME_ENDS********************/
                        }


                    }else if(!this.hasFrenzy){
                        /*****************GAME_ENDS********************/
                        this.endGame();
                        /*****************GAME_ENDS********************/
                    }

                }

            }

        }

        this.model.getBoard().refillSquares();


        //iterates the current player
        if (this.model.getPlayerList().indexOf(this.model.getCurrentPlayer()) != this.model.getPlayerList().size() - 1) {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(this.model.getPlayerList().indexOf(model.getCurrentPlayer()) + 1));
        } else {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(0));
        }


        this.model.setTurn(this.model.getTurn() + 1);

    }


    /**
     * ask player
     * @author Gregorio Barzasi
     *
     */

    public void endGame(){

        this.view.displayLeaderboard();

    }

    public void startFrenzy(){
        this.model.setFrenzy(true);
    }


    public Square askPosition(){

        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int column = sc.nextInt();

        return this.model.getBoard().getMap().getSquareMatrix()[row][column];
    }
    public Ammo askAmmo(){

        Figure p = this.model.getCurrentPlayer();
        Scanner sc = new Scanner(System.in);
        int red = sc.nextInt();
        int blue = sc.nextInt();
        int yellow = sc.nextInt();

        Ammo a = new Ammo(red, blue, yellow);
        p.getPersonalBoard().removeAmmo(a);

        return a;
    }
    public Figure askOneTarget(){

        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        for (Player p : this.model.getPlayerList()) {
            if(p.getUsername().equals(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Determines the squares a player can reach within a given range
     *
     * @param p the Player inquiring on
     * @param range the range
     * @return the list of possible squares
     */

    public ArrayList<Square> canGo(Figure p, int range){

        int row;
        int column;

        ArrayList<Square> options = new ArrayList<>();

        for(row = 0; row < height; row++){
            for(column = 0; column < width; column++){

                if(p.distanceTo(this.getModel().getBoard().getMap().getSquareMatrix()[row][column])
                        < range){

                    options.add(this.getBoard().getMap().getSquareMatrix()[row][column]);

                }
            }
        }

        return options;

    }

    public void update(){
        String s = marshal.create().toString();
        for(Player p: model.getPlayerList())
            p.getView().sendsUpdate(s);
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

    public ControllerState getDiscardingWeapon() {
        return discardingWeapon;
    }

    public void setDiscardingWeapon(ControllerState discardingWeapon) {
        this.discardingWeapon = discardingWeapon;
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

    public String intToMap(int i){

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
}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.*;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;

import java.util.Scanner;

public class Controller {

    private static final int height = 3;
    private static final int width = 4;


    //State pattern states

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


    //current state patten state

    ControllerState currentState;


    private GameModel model;
    private Lobby lobby;
    private View view;

    //Starts the game from a lobby

    public Controller(Lobby lobby) {

        this.lobby = lobby;
        this.model = new GameModel(lobby, this);

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

        for (Figure figure : this.model.getPlayerList()) {

            if (figure.getPersonalBoard().getDamage().size() >= 11) {

                for (i = 0; i < this.model.getBoard().getTrack().getKillsTrack().size(); i++) {
                    if (this.model.getBoard().getTrack().getKillsTrack().get(i) == null) {
                        flag = i;
                    }
                }

                this.model.getBoard().getTrack().getKillsTrack().get(i).add(figure.getPersonalBoard().getDamage().get(10));
                if (figure.getPersonalBoard().getDamage().size() == 12) {
                    this.model.getBoard().getTrack().getKillsTrack().get(i).add(figure.getPersonalBoard().getDamage().get(11));
                }
                figure.die();
            }

        }

        this.model.getBoard().refillSquares();


        //iterates the current player
        if (this.model.getPlayerList().indexOf(this.model.getCurrentPlayer()) != this.model.getPlayerList().size() - 1) {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(this.model.getPlayerList().indexOf(model.getCurrentPlayer()) + 1));
        } else {
            this.model.setCurrentPlayer(this.model.getPlayerList().get(0));
        }
    }

    /**
     * ask player
     * @author Gregorio Barzasi
     *
     */

    public void endGame(){

        this.view.displayLeaderboard();

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

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
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
}

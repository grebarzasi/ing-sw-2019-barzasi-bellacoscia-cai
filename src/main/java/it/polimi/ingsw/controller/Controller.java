package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.SpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;

import java.rmi.Remote;
import java.util.Scanner;

public class Controller {

    private ControllerState asBotMoving;
    private ControllerState asBotShooting;
    private ControllerState moving;
    private  ControllerState picking;
    private ControllerState shooting;
    private ControllerState choosingMove;
    private ControllerState choosingPowerUpToUse;
    private ControllerState discardingPowerUp;
    private ControllerState discargindWeapon;
    private  ControllerState choosingWeapon;

    private GameModel model;
    private Lobby lobby;

    private ControllerState currentState;

    public Controller(GameModel model) {

        asBotMoving = new AsBotMoving(this);
        asBotShooting = new AsBotShooting(this);
        moving = new Moving(this);
        picking = new Picking(this);
        shooting = new Shooting(this);
        choosingMove = new ChoosingMove(this);
        choosingPowerUpToUse = new ChoosingPowerUpToUse(this);
        discardingPowerUp = new DiscardingPowerUp(this);
        discargindWeapon = new DiscardingWeapon(this);
        choosingWeapon = new ChoosingWeapon(this);


        this.model = model;

    }

    /**
     * ask player
     * @author Gregorio Barzasi
     *
     */


    public void startGame(String arg){

        this.model = new GameModel(this.lobby);
        this.model.setCurrentBoard(new Board(arg));
        this.model.setCurrentPlayer(this.model.getPlayerList().get(0));

    }

    public Square askPosition(){

        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int column = sc.nextInt();

        return this.model.getCurrentBoard().getMap().getSquareMatrix()[row][column];
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
}

package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.map.SpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Controller implements Remote {

    private GameModel model;
    private Lobby lobby;
    //private VirtualView view = new VirtualView();

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

    public void move(String args[]){

        Square moveto = (this.model.getCurrentBoard().getMap().getSquareMatrix()[Integer.parseInt(args[0])][Integer.parseInt(args[1])]);
        this.model.getCurrentPlayer().setPosition(moveto);

    }

    public void pickWeapon(String arg){
        ((Player)this.model.getCurrentPlayer()).addWeapon(((SpawnSquare)(this.model.getCurrentPlayer().getPosition())).getArmory().getWeaponList().get(Integer.parseInt(arg)));
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

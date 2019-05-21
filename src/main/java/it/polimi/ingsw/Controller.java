package it.polimi.ingsw;

import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;

import java.rmi.Remote;
import java.util.Scanner;

public class Controller implements Remote {

    private GameModel model = new GameModel();
    private VirtualView view = new VirtualView();

    /**
     * ask player
     * @author Gregorio Barzasi
     */


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

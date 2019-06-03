package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Shooting implements ControllerState {

    private Controller controller;
    private Weapon shootingWith;

    public Shooting(Controller controller) {
        this.controller = controller;
    }


    /**
     * Displays the list of available targets displays them through the view
     * the view then returns the chosen targets
     * Shoots the targets according to weapon effects
     */


    @Override
    public void choose() {


        //per andare al model: this.controller.getModel()
        //l'arma con cui si sta sparando e' this.shootingWith


        ArrayList<Figure> targetable = new ArrayList<>();

        /*
        should load the valid targets into  targetable
         */


    }


    @Override
    public void shoot() {


    }



    @Override
    public void move() {

    }

    @Override
    public void pick() {

    }


    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Weapon getShootingWith() {
        return shootingWith;
    }

    public void setShootingWith(Weapon shootingWith) {
        this.shootingWith = shootingWith;
    }
}

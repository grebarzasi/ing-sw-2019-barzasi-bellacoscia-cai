package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

        Set<Effect> effects;
        effects = shootingWith.getUsableEff();

        Effect choice = this.controller.getView().showEffects(effects);

        choice.executeEffect();

        ArrayList<Figure> targetable = new ArrayList<>();



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

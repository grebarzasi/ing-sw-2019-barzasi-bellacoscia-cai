package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class AsBot implements ControllerState{

    private Controller controller;

    private boolean hasMoved;
    private boolean hasShot;


    public AsBot(Controller controller) {
        this.controller = controller;

        this.hasMoved = false;
        this.hasShot = false;
    }

    /**
     * Passes a list of valid targets to the view, (the view displays the
     * choices and makes the player choose one)
     * <p>
     * Then proceeds to shoot the target in the face (adding one bot damage or mark)
     */

    @Override
    public void shoot() {

        if (hasShot == false) {

            ArrayList<Figure> targets = new ArrayList<>();
            for (Player p : this.controller.getModel().getPlayerList()) {
                if (this.controller.getModel().getBot().canSee(p)) ;
                targets.add(p);
            }

            Player toShoot = this.controller.getView().showMultipleTargets(targets);

            int index = this.controller.getModel().getPlayerList().indexOf(toShoot);
            this.controller.getModel().getBot().inflictDamage(1, this.controller.getModel().getPlayerList().get(index));

            this.controller.getModel().endTurn();
            this.controller.setCurrentState(this.controller.choosingMove);

        } else {
            this.controller.getView().displayMessage("Bot has no actions left this turn");
        }

    }

    @Override
    public void chooseWeapon() {

    }

    /**
     * Passes the view a list of valid targets (The view displays them
     * and makes the player choose one)
     *
     * Then moves the bot to the chosen position
     */

    @Override
    public void move() {

        if (hasMoved == false && hasShot == false) {
            ArrayList<Square> canGo = new ArrayList<>();
            this.controller.getModel().getBot().setPosition(this.controller.getView().showPossibleMoves(this.controller.getModel().getBot().canGo()));
        } else if (hasMoved == true && hasShot == false) {
            this.controller.getView().displayMessage("Bot has already moved this turn");
        } else if (hasShot == true) {
            System.out.print("Bot has no actions left this turn");
        }
    }

    @Override
    public void pick() {

    }

    @Override
    public void choose() {

    }

    @Override
    public void chooseMove() {

    }

    @Override
    public void discardPU() {

    }

    @Override
    public void discardWeapon() {

    }

    @Override
    public void usePU() {

    }

    @Override
    public void teleport(Square teleportHere) {

    }

    @Override
    public void tagback() {

    }

    @Override
    public void Scope(PowerUp choice) {

    }

    @Override
    public void Newton(PowerUp choice) {

    }

    @Override
    public void useNewton(Figure Target, Square moveTo) {

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class AsBot implements ControllerState{

    private Controller controller;

    public AsBot(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void shoot() {

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

    }

    @Override
    public void chooseWeapon() {

    }

    @Override
    public void move() {

        ArrayList<Square> canGo = new ArrayList<>();
        this.controller.getModel().getBot().setPosition(this.controller.getView().showPossibleMoves(this.controller.getModel().getBot().canGo()));

    }

    @Override
    public void pick() {

    }

    @Override
    public void choose(int choice) {

    }

    @Override
    public void chooseMove(String choice) {

    }

    @Override
    public void discardPU(PowerUp toDiscard) {

    }

    @Override
    public void discardWeapon(Weapon toDiscard) {

    }

    @Override
    public void usePU(PowerUp toUse) {

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

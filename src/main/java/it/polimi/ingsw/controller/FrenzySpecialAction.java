package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * State in which a player can move, reload and shoot
 */

public class FrenzySpecialAction implements ControllerState {


    private int range;
    private Controller controller;

    @Override
    public void command() {

        ArrayList<Square> options = this.controller.canGo(this.controller.getCurrentPlayer(), this.range);

        Square choice = this.controller.getView().showPossibleMoves(options);
        this.controller.getCurrentPlayer().setPosition(choice);

        while(this.controller.getView().showBoolean("Vuoi sfruttare la ricarica?")){

            ArrayList<Weapon> reloadOptions = this.controller.getCurrentPlayer().getWeaponsList();
            Weapon reloadChoice = this.controller.getView().showWeapon(reloadOptions);
            boolean check = reloadChoice.reload();

            if (check == false) {
                this.controller.getView().displayMessage("Non possiedi abbastanza risorse per caricare l'arma");
            }

        }

        //TODO shooting part

        this.controller.update();

    }

    public FrenzySpecialAction(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}

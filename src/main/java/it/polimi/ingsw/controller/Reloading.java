package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class Reloading implements ControllerState {

    private Controller controller;

    public Reloading(Controller controller) {
        this.controller = controller;
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

    @Override
    public void choose() {

        ArrayList<Weapon> options = this.controller.getCurrentPlayer().getWeaponsList();
        Weapon choice = this.controller.getView().showWeapon(options);

        Boolean check = choice.reload();

        if (check == false) {
            this.controller.getView().displayMessage("You don't have enough ammo to load that weapon, try another one");
        }

        this.controller.setCurrentState(this.controller.choosingMove);

    }


}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class DiscardingWeapon implements ControllerState {

    private Controller controller;

    public DiscardingWeapon(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void shoot(ArrayList<Player> target) {

    }

    @Override
    public void chooseWeapon(Weapon choice) {

    }

    @Override
    public void move(Square position) {

    }

    @Override
    public void pick(Square position) {

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

}

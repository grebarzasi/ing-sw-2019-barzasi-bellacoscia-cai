package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

public class ChoosingPowerUpToUse implements ControllerState {

    private Controller controller;

    public ChoosingPowerUpToUse(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

        PowerUp chosen = this.controller.getView().showPowerUp(this.controller.getCurrentPlayer().getPowerupList());

        switch (chosen.getName()) {
            case "Newton":
                this.controller.setCurrentState(this.controller.usingNewton);
            case "Teleporter":
                this.controller.getCurrentPlayer().removePowerUp(chosen);
                this.controller.setCurrentState(this.controller.teleporting);

        }
    }


}

package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.actions.Move;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;

/**
 * The type Newton.
 *
 * @author Carlo Bellacoscia
 */
public class Newton extends PowerUp {

    /**
     * Instantiates a new Newton.
     *
     * @param ammoOnDiscard the ammo on discard
     * @param name          the name
     */
    public Newton(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect() {
       Player target = (Player)super.getOwner().getControllerServer().askTarget(1);
       new Move(2).doAction(target);
    }

}
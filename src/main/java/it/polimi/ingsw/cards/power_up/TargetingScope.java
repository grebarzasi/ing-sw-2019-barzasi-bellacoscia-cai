package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.board.Board;
import it.polimi.ingsw.board.PlayerBoard;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;

/**
 * The type Targeting scope.
 *
 * @author Carlo Bellacoscia
 */
public class TargetingScope extends PowerUp {


    /**
     * Instantiates a new Targeting scope.
     *
     * @param ammoOnDiscard the ammo on discard
     * @param name          the name
     */
    public TargetingScope(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect() {
        Player owner = super.getOwner();
        Player target = (Player)super.getOwner().getControllerServer().askTarget(1);
        Ammo a = new Ammo();
        Token t = new Token();
        PlayerBoard ownerB = new PlayerBoard(owner);
        PlayerBoard targetB = new PlayerBoard(target);

        ownerB.removeAmmo(a);
        targetB.addDamage(t);

    }
}
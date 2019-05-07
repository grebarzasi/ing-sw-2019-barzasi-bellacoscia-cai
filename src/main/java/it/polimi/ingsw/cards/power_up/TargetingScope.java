package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.board.PlayerBoard;
import it.polimi.ingsw.cards.Ammo;

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
        Figure owner = super.getOwner();
        Figure target = super.getOwner().getControllerServer().askOneTarget();
        Ammo a = super.getOwner().getControllerServer().askAmmo();
        Token t = new Token(owner);
        PlayerBoard ownerB = new PlayerBoard(owner);
        PlayerBoard targetB = new PlayerBoard(target);

        ownerB.removeAmmo(a);
        targetB.addDamage(t);

    }
}
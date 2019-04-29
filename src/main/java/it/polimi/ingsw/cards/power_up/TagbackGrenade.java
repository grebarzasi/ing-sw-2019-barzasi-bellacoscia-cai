package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Token;
import it.polimi.ingsw.board.PlayerBoard;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;

/**
 * The type Tagback grenade.
 *
 * @author Carlo Bellacoscia
 */
public class TagbackGrenade extends PowerUp {


    /**
     * Instantiates a new Tagback grenade.
     *
     * @param ammoOnDiscard the ammo on discard
     * @param name          the name
     */
    public TagbackGrenade(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect() {
        Player owner = super.getOwner();
        Player target = (Player)super.getOwner().getControllerServer().askTarget(1);
        PlayerBoard board = new PlayerBoard(target);
        Token t = new Token(owner);

        board.addMark(t);
    }
}
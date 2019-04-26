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
    public void effect(Square s, Player p) {
       Move move = new Move();
       move.moveTo(s,p);
    }

    @Override
    public void effect(Player p) {

    }

    @Override
    public void effect(Player owner, Player target) {

    }
}
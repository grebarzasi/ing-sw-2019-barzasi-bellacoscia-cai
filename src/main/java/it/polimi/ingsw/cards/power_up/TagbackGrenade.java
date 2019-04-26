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
    public void effect(Player p) {
        PlayerBoard board = new PlayerBoard(p);
        Token t = new Token();

        board.addMark(t);
    }

    @Override
    public void effect(Player owner, Player target) {

    }

    @Override
    public void effect(Square s, Player p) {

    }

}
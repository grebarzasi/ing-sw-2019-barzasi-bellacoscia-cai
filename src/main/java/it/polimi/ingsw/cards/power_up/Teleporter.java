package it.polimi.ingsw.cards.power_up;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;


/**
 * The type Teleporter.
 *
 * @author Carlo Bellacoscia
 */
public class Teleporter extends PowerUp {

    /**
     * Instantiates a new Teleporter.
     *
     * @param ammoOnDiscard the ammo on discard
     * @param name          the name
     */
    public Teleporter(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect(Square s, Player p) {
        p.setPosition(s);
    }

    @Override
    public void effect(Player p) {

    }

    @Override
    public void effect(Player owner, Player target) {

    }

}
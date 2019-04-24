package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.Action;
import it.polimi.ingsw.Move;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;

public class Newton extends PowerUp {

    public Newton(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect(Square s, Player p) {
       Move move = new Move();
       move.moveTo(s,p);
    }

    @Override
    public void effect() {

    }

    @Override
    public void effect(Player p) {

    }

    @Override
    public void effect(Player owner, Player target) {

    }
}
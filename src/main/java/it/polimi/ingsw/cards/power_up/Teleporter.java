package it.polimi.ingsw.cards.power_up;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.Square;
import it.polimi.ingsw.cards.Ammo;
import it.polimi.ingsw.cards.power_up.PowerUp;


public class Teleporter extends PowerUp {

    public Teleporter(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect() {
        Player target = (Player)super.getOwner().getControllerServer().askTarget(1);
        Square dest = super.getOwner().getControllerServer().askPosition();
        target.setPosition(dest);
    }
}
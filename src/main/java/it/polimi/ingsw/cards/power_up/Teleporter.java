package it.polimi.ingsw.cards.power_up;
import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.Ammo;


public class Teleporter extends PowerUp {

    public Teleporter(Ammo ammoOnDiscard, String name) {
        super(ammoOnDiscard, name);
    }

    @Override
    public void effect() {
        Figure target = super.getOwner().getControllerServer().askOneTarget();
        Square dest = super.getOwner().getControllerServer().askPosition();
        target.setPosition(dest);
    }
}
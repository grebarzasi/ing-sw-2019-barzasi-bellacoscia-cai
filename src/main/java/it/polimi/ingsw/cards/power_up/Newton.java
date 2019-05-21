package it.polimi.ingsw.cards.power_up;

import it.polimi.ingsw.actions.Move;
import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
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

    public void effect(Figure target, Square moveTo) {
       if(target.distanceTo(moveTo)<= 2 &&
                ((target.getPosition().getPosition().getColumn() == moveTo.getPosition().getColumn()) ||
                (target.getPosition().getPosition().getRow() == moveTo.getPosition().getRow())))
        {
            target.setPosition(moveTo);
        }else{
                    System.out.println("Select a valid target");
        }


    }


}
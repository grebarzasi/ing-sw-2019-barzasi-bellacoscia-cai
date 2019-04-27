package it.polimi.ingsw.actions;

import it.polimi.ingsw.Player;
/**
 * @author Gregorio Barzasi
 */
public class Pick implements SubAction {

    public Pick() {
    }


    public void doAction(Player p){
        p.getPosition().pickItem(p);
    }

}
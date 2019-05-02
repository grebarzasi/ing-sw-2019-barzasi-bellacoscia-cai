package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;

/**
 * @author Gregorio Barzasi
 */
public class Pick implements SubAction {

    public Pick() {
    }


    public void doAction(Figure p){
        p.getPosition().pickItem(p);
    }

}
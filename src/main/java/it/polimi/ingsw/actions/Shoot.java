package it.polimi.ingsw.actions;


import it.polimi.ingsw.Figure;

/**
 * @author Gregorio Barzasi
 */

public class Shoot implements SubAction {

    public Shoot() {
    }

    public void doAction(Figure p){
        p.getControllerServer().askWeapon().use();
    }

}
package it.polimi.ingsw.actions;


import it.polimi.ingsw.Player;

/**
 * @author Gregorio Barzasi
 */

public class Shoot implements SubAction {

    public Shoot() {
    }

    public void doAction(Player p){
        p.getControllerServer().askWeapon().use();
    }

}
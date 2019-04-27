package it.polimi.ingsw.actions;


import it.polimi.ingsw.Player;


/**used by {@link Action}
 * @author Gregorio Barzasi
 */

public interface SubAction {
    void doAction(Player actor);
}
package it.polimi.ingsw.actions;


import it.polimi.ingsw.Figure;


/**used by {@link Action}
 * @author Gregorio Barzasi
 */

public interface SubAction {
    void doAction(Figure actor);
}
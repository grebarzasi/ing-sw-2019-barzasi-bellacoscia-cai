package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * this class is used by {@link GameModel} to initialize {@link Action} according to game status
 * @author Gregorio Barzasi
 */
public class ActionBuilder {

    public static Set<Action> build(Figure p, boolean isFrenzy) {

        if (!isFrenzy) {

            int damage = p.getPersonalBoard().getDamage().size();
            int adrenalineStage;

            HashSet<Action> actions = new HashSet<>();

            adrenalineStage = 0;

            if (damage > 2) {
                adrenalineStage = 1;
            }

            if (damage > 5) {
                adrenalineStage = 2;
            }

            actions.add(new Action("Move", 3));

            if (adrenalineStage == 0) {

                actions.add(new Action("Pick", 1));
                actions.add(new Action("Shoot", 0));

            } else if (adrenalineStage == 1) {

                actions.add(new Action("Pick", 2));
                actions.add(new Action("Shoot", 0));

            } else if (adrenalineStage == 2) {

                actions.add(new Action("Pick", 2));
                actions.add(new Action("Shoot", 1));

            }

            return actions;

        } else {

            return new HashSet<>();
        }

    }

}

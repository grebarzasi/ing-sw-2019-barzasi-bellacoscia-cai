package it.polimi.ingsw.actions;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.GameModel;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Genetrates actions a player can perform
 */

public class ActionBuilder {

    private static final int stage1 = 2;
    private static final int stage2 = 5;

    public static final int stage0PickRange = 1;
    public static final int stage0ShootRange = 0;
    public static final int stage0MoveRange = 3;

    public static final int stage1PickRange = 2;
    public static final int stage1ShootRange = 0;

    public static final int stage2PickRange = 2;
    public static final int stage2ShootRange = 1;

    public static final int frenzyMoveRange = 4;
    public static final int frenzyPickRange = 2;
    public static final int frenzyMoveReloadShootRange = 1;

    public static final int firstPlayerFrenzyPickRange = 3;
    public static final int firstPlayerFrenzyMoveReloadShootRange = 2;


    //TODO Generate bot action

    /**
     * Generates available actions based on information taken from the game
     *
     * @param p the player
     * @param isFrenzy frenzy status of the game
     * @return the ArrayList of actions a player can perform
     */

    public static ArrayList<Action> build(Player p, boolean isFrenzy) {

        ArrayList<Action> actions = new ArrayList<>();


        if (p.getModel().getMovesLeft() > 0) {

            if (!isFrenzy) {
                int damage = p.getPersonalBoard().getDamage().size();
                int adrenalineStage;

                adrenalineStage = 0;

                if (damage > stage1) {
                    adrenalineStage = 1;
                }

                if (damage > stage2) {
                    adrenalineStage = 2;
                }

                actions.add(new Action("Move", stage0MoveRange));

                if (adrenalineStage == 0) {

                    actions.add(new Action("Pick", stage0PickRange));
                    actions.add(new Action("Shoot", stage0ShootRange));

                } else if (adrenalineStage == 1) {

                    actions.add(new Action("Pick", stage1PickRange));
                    actions.add(new Action("Shoot", stage1ShootRange));

                } else if (adrenalineStage == 2) {

                    actions.add(new Action("Pick", stage2PickRange));
                    actions.add(new Action("Shoot", stage2ShootRange));

                }


            } else {

                if (!p.getStartedFrenzy()) {
                    actions.add(new Action("Move", frenzyMoveRange));
                    actions.add(new Action("Pick", frenzyPickRange));
                    actions.add(new Action("Move, reload and Shoot", frenzyMoveReloadShootRange));
                } else {
                    actions.add(new Action("Pick", firstPlayerFrenzyPickRange));
                    actions.add(new Action("Move, reload and Shoot", firstPlayerFrenzyMoveReloadShootRange));

                }
            }

        } else {
            actions.add(new Action("Reload", 0));
        }

        for(PowerUp pu : p.getPowerupList()){

            boolean flag = false;

            if(pu.getName() == "Teleporter" || pu.getName() == "Newton"){
                flag = true;
            }
            if(flag == true){
                actions.add(new Action("PowerUp",0));
            }

        }

        return actions;
    }

}

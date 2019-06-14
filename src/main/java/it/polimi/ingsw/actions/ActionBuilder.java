package it.polimi.ingsw.actions;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;

/**
 * Genetrates actions a player can perform
 */

public class ActionBuilder {

    private static final String SHOOT = "Shoot";

    private static final int STAGE_1 = 2;
    private static final int STAGE_2 = 5;

    private static final int STAGE_0_PICK_RANGE = 1;
    private static final int STAGE_0_SHOOT_RANGE = 0;
    private static final int STAGE_0_MOVE_RANGE = 3;

    private static final int STAGE_1_PICK_RANGE = 2;
    private static final int STAGE_1_SHOOT_RANGE = 0;

    private static final int STAGE_2_PICK_RANGE = 2;
    private static final int STAGE_2_SHOOT_RANGE = 1;

    private static final int FRENZY_MOVE_RANGE = 4;
    private static final int FRENZY_PICK_RANGE = 2;
    private static final int FRENZY_MOVE_RELOAD_SHOOT_RANGE = 1;

    private static final int FIRST_PLAYER_FRENZY_PICK_RANGE = 3;
    private static final int FIRST_PLAYER_FRENZY_MOVE_RELOAD_SHOOT_RANGE = 2;


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

                if (damage > STAGE_1) {
                    adrenalineStage = 1;
                } else if (damage > STAGE_2) {
                    adrenalineStage = 2;
                }

                actions.add(new Action("Move", STAGE_0_MOVE_RANGE));

                if (adrenalineStage == 0) {

                    actions.add(new Action("Pick", STAGE_0_PICK_RANGE));
                    if(canShoot(p)) {
                        actions.add(new Action(SHOOT, STAGE_0_SHOOT_RANGE));
                    }

                } else if (adrenalineStage == 1) {

                    actions.add(new Action("Pick", STAGE_1_PICK_RANGE));
                    if(canShoot(p)) {
                        actions.add(new Action(SHOOT, STAGE_1_SHOOT_RANGE));
                    }

                } else if (adrenalineStage == 2) {

                    actions.add(new Action("Pick", STAGE_2_PICK_RANGE));
                    if(canShoot(p)) {
                        actions.add(new Action(SHOOT, STAGE_2_SHOOT_RANGE));
                    }

                }


            } else {

                if (!p.getStartedFrenzy()) {
                    actions.add(new Action("Move", FRENZY_MOVE_RANGE));
                    actions.add(new Action("Pick", FRENZY_PICK_RANGE));
                    if(canShoot(p)) {
                        actions.add(new Action(SHOOT, FRENZY_MOVE_RELOAD_SHOOT_RANGE));
                    }
                } else {
                    actions.add(new Action("Pick", FIRST_PLAYER_FRENZY_PICK_RANGE));
                    if(canShoot(p)) {
                        actions.add(new Action(SHOOT, FIRST_PLAYER_FRENZY_MOVE_RELOAD_SHOOT_RANGE));
                    }

                }
            }

        } else{

            boolean canReload = false;

            for(Weapon w: p.getWeaponsList()){
                if(!w.isLoaded()){
                    canReload = true;
                }
            }

            if(canReload) {
                actions.add(new Action("Reload", 0));
            }

        }

        generatePowerUpActions(p,actions);
        

        if(p.getModel().hasBotAction() && p.getModel().getController().hasBot()){
            actions.add(new Action("Use Bot",0));
        }


        actions.add(new Action("End Turn", 0));

        return actions;

    }
    
    
    private static void generatePowerUpActions(Player p, ArrayList<Action> actions){
        
        if(!p.getPowerupList().isEmpty()){

            actions.add(new Action("Discard PowerUp",0));

            boolean flag = false;

            for(PowerUp pu : p.getPowerupList()){

                if(pu.getName().equals("Teletrasporto") || pu.getName().equals("Raggio Cinetico")){
                    flag = true;
                }
            }
            if(flag == true){
                actions.add(new Action("PowerUp",0));
            }
        }
        
    }
    
    private static boolean canShoot(Player p){
        for(Weapon w: p.getWeaponsList()){
            if(w.isLoaded()){
                return true;
            }
        }
        return false;
    }


}

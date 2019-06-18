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

        //If it's not the end of the turn generates Move, Pick and Shoot
        if (p.getModel().getMovesLeft() > 0) {

            //If it's not frenzy state generate normal actions
            if (!isFrenzy) {
                generateNonFrenzyActions(p,actions);

            //If it's frenzy state generate frenzy actions
            } else {
                generateFrenzyActions(p,actions);
            }

        //If it is the end of the turn generate reload
        }else{
            generateReloading(p,actions);
        }

        //In the end check if a bot or powerup can be used and add those actions always
        generatePowerUpActions(p,actions);
        generateBotActions(p,actions);
        return actions;
    }

    /**
     * Generates the actions of the bot
     *
     * @param p the referral player
     * @param actions action list to load into
     */

    private static void generateBotActions(Player p, ArrayList<Action> actions){
        if(p.getModel().hasBotAction() && p.getModel().getController().hasBot()){
            actions.add(new Action("Use Bot",0));
        }
        actions.add(new Action("End Turn", 0));
    }

    /**
     * Generates non frenzy actions of move, pick and shoot
     *
     * @param p the referral player
     * @param actions action list to load into
     */

    private static void generateNonFrenzyActions(Player p, ArrayList<Action> actions){

        int damage = p.getPersonalBoard().getDamage().size();
        int adrenalineStage;

        adrenalineStage = 0;


        if (damage > STAGE_1) {
            adrenalineStage = 1;
        } else
            //noinspection ConstantConditions
            if (damage > STAGE_2) {
            adrenalineStage = 2;
        }

        actions.add(new Action("Move", STAGE_0_MOVE_RANGE));

        if (adrenalineStage == 0) {

            actions.add(new Action("Pick", STAGE_0_PICK_RANGE));
            if(canShoot(p)) {
                actions.add(new Action(SHOOT, STAGE_0_SHOOT_RANGE));
            }

        } else
            //noinspection ConstantConditions
            if (adrenalineStage == 1) {

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

    }

    /**
     * Generates the reloading action if the player is at the rnd of a turn
     *
     * @param p the referral player
     * @param actions action list to load into
     */

    private static void generateReloading(Player p, ArrayList<Action> actions){

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

    /**
     * Generates the frenzy actions when frenzy is initiated
     *
     * @param p the referral player
     * @param actions action list to load into
     */

    private static void generateFrenzyActions(Player p, ArrayList<Action> actions){

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

    /**
     * Generates the PowerUp actions if a player can use a valid one
     *
     * @param p the referral player
     * @param actions action list to load into
     */

    private static void generatePowerUpActions(Player p, ArrayList<Action> actions){
        
        if(!p.getPowerupList().isEmpty()){

            actions.add(new Action("Discard PowerUp",0));

            boolean flag = false;

            for(PowerUp pu : p.getPowerupList()){

                if(pu.getName().equals(PowerUp.TELEPORTER ) || pu.getName().equals(PowerUp.NEWTON)){
                    flag = true;
                }
            }
            if(flag){
                actions.add(new Action("PowerUp",0));
            }
        }
        
    }

    /**
     * Asserts if a player can shoot
     * @param p the referral player
     * @return true if the player has a loaded weapon, false otherwise
     */
    
    private static boolean canShoot(Player p){
        for(Weapon w: p.getWeaponsList()){
            if(w.isLoaded()){
                return true;
            }
        }
        return false;
    }


}

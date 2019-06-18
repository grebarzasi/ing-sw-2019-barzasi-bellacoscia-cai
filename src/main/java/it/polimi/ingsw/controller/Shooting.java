package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.MoveTarget;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.cards.weapon.aiming.AimAskPlayer;
import it.polimi.ingsw.cards.weapon.aiming.AimDirection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * State of shooting enemies in the face
 */

public class Shooting implements ControllerState {

    private int range;
    private Controller controller;
    private Weapon shootingWith;

    Shooting(Controller controller) {
        this.controller = controller;
    }


    /**
     * Displays the list of available targets displays them through the view
     * the view then returns the chosen targets
     * Shoots the targets according to weapon effects
     */


    @Override
    public void command(){


        boolean additionalEffect = false;
        boolean scopeUsed=false;
        boolean ok;
        AimDirection dir;
        AimAskPlayer ask;
        MoveTarget mv;

        Set<Effect> effects;


        //Execute until no more effect is left or player go back
        do{
            effects = shootingWith.getUsableEff();

            //if empty no effect are available and you can go on
            if(effects.isEmpty()) {
                break;
            }

            Effect choice = this.controller.getView().showEffects(effects);

            //if you already used a effect and you exit the second choose you can go on
            if(choice==null) {
                break;
            }


            //if the player can afford the effect execute

            if(this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().covers(choice.getCost())) {
                //repeat until no more action from player are required

                do {

                    if (shootingWith.getDirectionTemp() != null) {

                        dir = shootingWith.getDirectionTemp();
                        String rpl = controller.getView().chooseDirection(new ArrayList<>(dir.getTargetTemp()));
                        checkReplyNull(rpl);
                        dir.setDirectionTemp(rpl);

                    } else if (shootingWith.getAskTemp() != null) {

                        ask = shootingWith.getAskTemp();
                        ArrayList<Figure> rpl = controller.getView().showTargetAdvanced(ask.getTargetTemp(), ask.getNumMax(), ask.isFromDiffSquare(), ask.getMsg());
                        checkReplyNull(rpl);
                        ask.setTargetTemp(new HashSet<>(rpl));

                    }else if (shootingWith.getMoveTemp() != null) {

                        mv = shootingWith.getMoveTemp();
                        ArrayList<Square> options = this.controller.canGo((Figure)mv.getTargetTemp().toArray()[0],mv.getMaxSteps());
                        Square rpl = controller.getView().showPossibleMoves(options,false);
                        checkReplyNull(rpl);
                        mv.setSquareTemp(rpl);
                    }

                    ok = choice.executeEffect();
                    //this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().substract(choice.getCost());


                } while (!ok);

                if(!additionalEffect) {
                    this.controller.decreaseMoveLeft();
                }
                //now the effect is been executed

                additionalEffect=true;

                if(!scopeUsed)
                    scopeUsed=this.useScope(choice);


                this.controller.update();

            }else{
                if(additionalEffect)
                    shootingWith.resetWeapon();
                this.controller.update();
                this.controller.goBack();
            }

            askVenoms(choice.getTargetHitSet());

        } while (true);


        shootingWith.resetWeapon();
        this.controller.goBack();



    }

    private void askVenoms(Set<Figure> targets){

        if(this.controller.hasBot()) {
            targets.remove(this.controller.getModel().getBot());
        }

        ArrayList<Player> finalTargets = new ArrayList<>();

        for(Figure p : targets){
            finalTargets.add((Player)p);
        }

        Player tmp = this.controller.getCurrentPlayer();
        int i;

        for(i = 0 ; i < finalTargets.size() ; i++){

            ArrayList<PowerUp> filtered = new ArrayList<>(finalTargets.get(i).getPowerupList());
            Controller.filterPUs(filtered,PowerUp.TAGBACK_GRENADE);

            if(filtered.isEmpty()){
                finalTargets.remove(i);
            }
        }

        for(Player p : finalTargets){

            this.controller.getModel().setCurrentPlayer(p);
            this.controller.setView(this.controller.getCurrentPlayer().getView());

            boolean useTagback = this.controller.getView().showBoolean("Vuoi usare la Granata Venom? \n");

            if(useTagback){

                ArrayList<PowerUp> options = new ArrayList<>(p.getPowerupList());

                Controller.filterPUs(options,PowerUp.TAGBACK_GRENADE);

                PowerUp choice = this.controller.getView().showPowerUp(options);


                if(choice != null){
                    p.inflictMark(1,tmp);
                    p.removePowerUp(choice);
                }

            }

                this.controller.update();

        }

        this.controller.getModel().setCurrentPlayer(tmp);

    }


    private boolean useScope(Effect chosen){

        if(this.canUseScope())
            return this.activateScope(chosen);
        return false;

    }

    private boolean canUseScope(){

        if(!this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().isEmpty()) {

            for (PowerUp p : this.controller.getCurrentPlayer().getPowerupList()) {
                if (p.getName().equals(PowerUp.TARGETING_SCOPE)) {
                    return true;
                }
            }

            return false;

        }else{

            return false;
        }

    }

    private boolean activateScope(Effect chosen){

        boolean useScope = this.controller.getView().showBoolean("Vuoi usare anche il mirino?: \n");

        if (useScope) {

            ArrayList<PowerUp> scopes = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());

            Controller.filterPUs(scopes, PowerUp.TARGETING_SCOPE);

            PowerUp toUse = this.controller.getView().showPowerUp(scopes);

            Set<Figure> options = new HashSet<>(chosen.getTargetHitSet());

            ArrayList<Figure> chosenTarget = this.controller.getView().showTargetAdvanced(options,
                    1, false, "Scegli il bersaglio: \n");

            this.controller.getCurrentPlayer().inflictDamage(1, chosenTarget.get(0));
            this.controller.getCurrentPlayer().removePowerUp(toUse);
            return true;
        }
        return false;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    void setShootingWith(Weapon shootingWith) {
        this.shootingWith = shootingWith;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    private void checkReplyNull(Object rpl){
        if (rpl == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            shootingWith.resetWeapon();
            controller.endTurn();
        } else  if (rpl == null) {
            shootingWith.resetWeapon();
            this.controller.goBack();
        }
    }
}

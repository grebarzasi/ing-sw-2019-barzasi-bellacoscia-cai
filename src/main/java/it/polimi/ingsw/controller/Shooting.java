package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
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
    public void executeState(){

        Effect choice=null;
        boolean additionalEffect = false;
        boolean scopeUsed=false;
        boolean ok;

        this.move();

        Set<Effect> effects;


        //Execute until no more effect is left or player go back
        do{
            effects = shootingWith.getUsableEff();

            //if empty no effect are available and you can go on
            if(effects.isEmpty()) {
                break;
            }

            choice = this.controller.getView().showEffects(effects);

            //if null escape ( it mean you want to go back or paused for inactivity)
            if(choice==null) {
                break;
            }


            //if the player can afford the effect execute
            if(this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().covers(choice.getCost())) {

                //repeat until no more action from player are required
                do {
                    checkInfoNeeded(shootingWith);
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

            }else
                controller.getView().displayMessage("Non hai le risorse necessarie per questo effetto");

            this.controller.askVenoms(choice.getTargetHitSet() , this.controller.getCurrentPlayer());

        } while (true);

        checkReply(choice);

        this.controller.update();
        this.controller.goBack();

    }

    private void move(){

        if(this.range!= 0){
            ArrayList<Square> options = this.controller.getCurrentPlayer().canGo(1);
            Square choice = this.controller.getView().showPossibleMoves(options,false);

            if(choice != null) {
                this.controller.getCurrentPlayer().setPosition(choice);
            }else{
                this.controller.goBack();
            }
        }

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

        boolean useScope = this.controller.getView().showBoolean(ControllerMessages.ASK_SCOPE);

        if (useScope) {

            ArrayList<PowerUp> scopes = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());

            Controller.filterPUs(scopes, PowerUp.TARGETING_SCOPE);

            PowerUp toUse = this.controller.getView().showPowerUp(scopes);
            if(toUse==null)
                return false;

            Set<Figure> options = new HashSet<>(chosen.getTargetHitSet());

            ArrayList<Figure> chosenTarget = this.controller.getView().showTargetAdvanced(options,
                    1, false, ControllerMessages.ASK_TARGET);

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

    private void checkReply(Object rpl){
        if (rpl == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            shootingWith.resetWeapon();
            controller.endTurn();
        } else  if (rpl == null) {
            shootingWith.resetWeapon();
            this.controller.update();
            this.controller.goBack();
        }
    }

    private void checkInfoNeeded(Weapon w){
        AimDirection dir;
        AimAskPlayer ask;
        MoveTarget mv;

        if (w.getDirectionTemp() != null) {

            dir = w.getDirectionTemp();
            String rpl = controller.getView().chooseDirection(new ArrayList<>(dir.getTargetTemp()));
            checkReply(rpl);
            dir.setDirectionTemp(rpl);
            return;
        }
        if (w.getAskTemp() != null) {

            ask = w.getAskTemp();
            ArrayList<Figure> rpl = controller.getView().showTargetAdvanced(ask.getTargetTemp(), ask.getNumMax(), ask.isFromDiffSquare(), ask.getMsg());
            checkReply(rpl);
            ask.setTargetTemp(new HashSet<>(rpl));
            return;
        }
        if (w.getMoveTemp() != null) {

            mv = w.getMoveTemp();
            ArrayList<Square> options = this.controller.canGo((Figure)mv.getTargetTemp().toArray()[0],mv.getMaxSteps());
            Square rpl = controller.getView().showPossibleMoves(options,false);
            checkReply(rpl);
            mv.setSquareTemp(rpl);
        }
    }
}

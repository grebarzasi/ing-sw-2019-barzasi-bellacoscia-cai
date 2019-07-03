package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.MoveTarget;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.cards.weapon.aiming.AimAskPlayer;
import it.polimi.ingsw.cards.weapon.aiming.AimDirection;
import it.polimi.ingsw.cards.weapon.aiming.AimSquare;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static it.polimi.ingsw.controller.ControllerMessages.*;

/**
 * State of shooting enemies in the face
 */

public class Shooting implements ControllerState {

    /**
     * Movements that the player can perform before shooting
     */
    private int range;

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * The weapon the player is shooting with
     */
    private Weapon shootingWith;


    boolean additionalEffect = false;
    boolean scopeUsed=false;
    int oldDamagedSize;

    /**
     * Default constructor
     * @param controller the reference controller
     */
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

        this.move();

        Set<Effect> effects;


        //Execute until no more effect is left or player go back
        do{
            oldDamagedSize= shootingWith.getDamaged().size();
            effects = shootingWith.getUsableEff();

            //if empty no effect are available and you can go on
            if(effects.isEmpty()) {
                controller.getView().displayMessage(NO_USABLE_EFFECT);
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
                useEffect(choice);
                postShootCheck(choice);

                this.controller.update();



            }else {
                controller.getView().displayMessage(NO_AMMO);
            }


        } while (true);

        additionalEffect=false;
        choice=null;
        checkReply(choice);
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

    /**
     * if you can use scope, use it.
     */
    private void useScope(){
        if(this.canUseScope())
            this.activateScope();

    }
    /**
     * check if you can use scope or not
     */
    private boolean canUseScope(){

        if(!this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().isEmpty()&&!shootingWith.getDamaged().isEmpty()) {

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

    /**
     * use the scope powerup
     */
    private boolean activateScope(){

        Boolean useScope = this.controller.getView().showBoolean(ControllerMessages.ASK_SCOPE);

        if (useScope!=null&&useScope) {

            ArrayList<PowerUp> scopes = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());

            Controller.filterPUs(scopes, PowerUp.TARGETING_SCOPE);

            PowerUp toUse = this.controller.getView().showPowerUp(scopes);
            if(toUse==null)
                return false;

            Set<Figure> options = new HashSet<>(shootingWith.getDamaged());

            ArrayList<Figure> chosenTarget = this.controller.getView().showTargetAdvanced(options,
                    1, false, ControllerMessages.ASK_TARGET);

            this.controller.getCurrentPlayer().inflictDamage(1, chosenTarget.get(0));
            this.controller.getCurrentPlayer().removePowerUp(toUse);
            return true;
        }
        return false;
    }

    /**
     * check if the nullity of rpl is caused by a disconnection / inactivity or the player want to go back
     * @author Gregorio Barzasi
     */
    private void checkReply(Object rpl){
        if (rpl == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            shootingWith.resetWeapon();
            controller.endTurn();
        }else if (rpl == null){
            useScope();
            this.controller.askTagbacks(this.shootingWith.getDamaged(), this.controller.getCurrentPlayer());
            shootingWith.resetWeapon();
            this.controller.update();
            this.controller.goBack();
        }
    }
    /**
     * use the weapon effect
     * @author Gregorio Barzasi
     */
    private void useEffect(Effect choice){
        boolean ok;
        do {
            checkInfoNeeded(shootingWith);
            ok = choice.executeEffect();
        } while (!ok);
    }

    /**
     * check weapon hit someone and if true subtract the cost of that effect to Ammo Inventory
     * @author Gregorio Barzasi
     */

    private void postShootCheck(Effect choice){
        //if you shoot someone decrease the cost and set unloaded
        if(!choice.getTargetHitSet().isEmpty()||oldDamagedSize!=shootingWith.getDamaged().size()) {
            this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().subtract(choice.getCost());

             shootingWith.setLoaded(false);

            //if is not your addition effect, set move used

            if(!additionalEffect) {
                this.controller.decreaseMoveLeft();
            }

            //now the effect is been executed
            additionalEffect=true;


        }else {
            //if you miss your hit reset effect
            controller.getView().displayMessage(NO_HIT);
            choice.resetEffect();
        }

    }

    /**
     * check if a input from player is needed to execute effect. it pauses the weapon used an after the info is received
     * the state of the weapon is restored and the effect applied
 * @author Gregorio Barzasi
 */
    private void checkInfoNeeded(Weapon w){
        AimDirection dir;
        AimAskPlayer ask;
        MoveTarget mv;
        AimSquare sq;

        if (w.getSquareTemp() != null) {
            sq = w.getSquareTemp();
            ArrayList<Square> options = this.controller.visibleSquare((Figure)sq.getTargetTemp().toArray()[0]);
            if(!sq.isMine())
                options.remove(shootingWith.getOwner().getPosition());
            Square rpl = controller.getView().showPossibleMoves(options,false);
            checkReply(rpl);
            shootingWith.setSelected(rpl);
            Set<Figure> temp= new HashSet<>(controller.playersInRange(rpl,sq.getMinDistance(),sq.getMaxDistance()));
            temp.remove(shootingWith.getOwner());
            sq.setTargetTemp(temp);
            shootingWith.setPrevSelected(new HashSet<>(temp));
            return;
        }
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
            ArrayList<Square> options = this.controller.canGo((Figure)mv.getTargetTemp().toArray()[0],mv.getMaxSteps(),mv.isDirectional());
            Square rpl = controller.getView().showPossibleMoves(options,false);
            checkReply(rpl);
            mv.setSquareTemp(rpl);
        }
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

}

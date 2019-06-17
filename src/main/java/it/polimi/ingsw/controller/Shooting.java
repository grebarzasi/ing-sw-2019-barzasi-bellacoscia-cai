package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.cards.weapon.aiming.AimAskPlayer;
import it.polimi.ingsw.cards.weapon.aiming.AimDirection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * State of shooting enemies in the face
 */

public class Shooting implements ControllerState {

    private int range;
    private Controller controller;
    private Weapon shootingWith;

    public Shooting(Controller controller) {
        this.controller = controller;
    }


    /**
     * Displays the list of available targets displays them through the view
     * the view then returns the chosen targets
     * Shoots the targets according to weapon effects
     */


    @Override
    public void command() throws IOException {

        boolean ok = false;
        AimDirection dir;
        AimAskPlayer ask;

        Set<Effect> effects;
        effects = shootingWith.getUsableEff();

        Effect choice = this.controller.getView().showEffects(effects);

        HashMap<Player,Integer> revengeCheck = new HashMap<>();

        checkNull(choice);

        if(this.controller.getCurrentPlayer().getPersonalBoard().getAmmoInventory().covers(choice.getCost())) {

            do {

                if (shootingWith.getDirectionTemp() != null) {

                    dir = shootingWith.getDirectionTemp();
                    String rpl = controller.getView().chooseDirection(new ArrayList<>(dir.getTargetTemp()));

                    if (rpl == null) {

                        shootingWith.resetWeapon();
                        this.controller.goBack();

                    }

                    dir.setDirectionTemp(rpl);

                } else if (shootingWith.getAskTemp() != null) {

                    ask = shootingWith.getAskTemp();
                    ArrayList<Figure> rpl = controller.getView().showTargetAdvanced(ask.getTargetTemp(), ask.getNumMax(), ask.isFromDiffSquare(), ask.getMsg());
                    if (rpl == null) {

                        shootingWith.resetWeapon();
                        this.controller.goBack();

                    }
                    ask.setTargetTemp(new HashSet<>(rpl));
                }

                ok = choice.executeEffect();

            } while (!ok);

            this.useScope();

            shootingWith.resetWeapon();
            this.controller.update();
            this.controller.dereaseMoveLeft();
            this.controller.goBack();

        }else {
            this.controller.update();
            this.controller.goBack();
        }

    }

    private void checkNull(Effect choice)throws IOException{
        if(choice==null){
            this.controller.goBack();
            //this.controller.choosingMove.command(); goBack already does command
        }


    }

    private void useScope()throws IOException{

        if(this.canUseScope()) {

            this.activateScope();

        }

    }

    public boolean canUseScope(){

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

    private void activateScope()throws IOException {

        boolean useScope = this.controller.getView().showBoolean("Vuoi usare anche il mirino?: ");

        if (useScope) {

            ArrayList<PowerUp> scopes = new ArrayList<>();
            scopes.addAll(this.controller.getCurrentPlayer().getPowerupList());

            Controller.filterPUs(scopes, PowerUp.TARGETING_SCOPE);

            PowerUp toUse = this.controller.getView().showPowerUp(scopes);

            ArrayList<Figure> options = new ArrayList<>();
            options.addAll(this.shootingWith.getBasicEffect().getTargetHitSet());

            ArrayList<Figure> chosenTarget = this.controller.getView().showTargetAdvanced(this.shootingWith.getBasicEffect().getTargetHitSet(),
                    1, false, "Scegli il bersaglio: ");

            this.controller.getCurrentPlayer().inflictDamage(1, chosenTarget.get(0));
            this.controller.getCurrentPlayer().removePowerUp(toUse);
        }

    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Weapon getShootingWith() {
        return shootingWith;
    }

    public void setShootingWith(Weapon shootingWith) {
        this.shootingWith = shootingWith;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

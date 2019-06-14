package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
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

    public Shooting(Controller controller) {
        this.controller = controller;
    }


    /**
     * Displays the list of available targets displays them through the view
     * the view then returns the chosen targets
     * Shoots the targets according to weapon effects
     */


    @Override
    public void command() {
        boolean ok=false;
        AimDirection dir;
        AimAskPlayer ask;

        Set<Effect> effects;
        effects = shootingWith.getUsableEff();

        Effect choice = this.controller.getView().showEffects(effects);
        if(choice==null){
            this.controller.goBack();
            this.controller.choosingMove.command();
        }

        do{
            if(shootingWith.getDirectionTemp()!=null) {
                dir = shootingWith.getDirectionTemp();
                String rpl = controller.getView().chooseDirection(new ArrayList<>(dir.getTargetTemp()));
                if(rpl==null) {
                    shootingWith.resetWeapon();
                    this.controller.goBack();
                    this.controller.choosingMove.command();
                }
                dir.setDirectionTemp(rpl);
            }else if(shootingWith.getAskTemp()!=null){
                ask = shootingWith.getAskTemp();
                ArrayList<Figure> rpl = controller.getView().showTargetAdvanced(ask.getTargetTemp(),ask.getNumMax(),ask.isFromDiffSquare(),ask.getMsg());
                if(rpl==null) {
                    shootingWith.resetWeapon();
                    this.controller.goBack();
                    this.controller.choosingMove.command();
                }
                ask.setTargetTemp(new HashSet<>(rpl));
            }else
                ok=choice.executeEffect();
        }while (!ok);
        shootingWith.resetWeapon();
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

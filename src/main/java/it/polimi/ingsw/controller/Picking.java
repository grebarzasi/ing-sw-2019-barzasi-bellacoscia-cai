package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.NonSpawnSquare;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

/**
 * State of picking an ammo or weapon
 */

public class Picking implements ControllerState{

    private int range;
    private Controller controller;

    private static final int max = 3;

    public Picking(Controller controller) {
        this.controller = controller;
    }

    /**
     * Generates a list of squares the current player can pick items from based on range
     * and sends it to the view, then takes an input and picks the item or redirects to
     * another state according to player's choice
     */

    @Override
    public void command() {

        ArrayList<Square> options;

        options = this.controller.canGo(this.controller.getCurrentPlayer(),this.range);
        options.add(this.controller.getCurrentPlayer().getPosition());

        Square choice = this.controller.getView().showPossibleMoves(options, false);

        if(choice == null){
            this.controller.goBack();
        }else {

            if (choice.isSpawn()) {

                this.controller.setCurrentState(this.controller.pickingWeapon);
                this.controller.getCurrentState().command();

            } else {

                this.controller.getCurrentPlayer().setPosition(choice);
                PowerUp check = this.controller.getCurrentPlayer().pickAmmo();

                if(check == null){

                    this.controller.dereaseMoveLeft();
                    this.controller.update();
                    this.controller.goBack();

                }else{

                    this.choosePU(check);


                }


            }
        }


    }

    /**
     * Sends the power up options composed of player's 3 existing powerups and the drawn one
     * asks the player to discard one and puts the rest into the player's powerup list
     */

    private void choosePU(PowerUp check) {

        ArrayList<PowerUp> options = new ArrayList<>();
        options.addAll(this.controller.getCurrentPlayer().getPowerupList());
        options.add(check);

        PowerUp chosen = this.controller.getView().showPowerUp(options);


        if(chosen == null){

            chosen = options.get(getRandom(options.size(),0));
            options.remove(chosen);
            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(chosen);

            this.substitute(options);


        }else {

            options.remove(chosen);

            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(chosen);
            this.substitute(options);

        }

        this.controller.dereaseMoveLeft();
        this.controller.update();
        this.controller.goBack();

    }

    private void substitute(ArrayList<PowerUp> options ){

        this.controller.getCurrentPlayer().getPowerupList().clear();
        this.controller.getCurrentPlayer().getPowerupList().addAll(options);


    }


    private int getRandom(int max, int min){

        int rand = (int)Math.random()* ((max - min) + 1) + min;

        return rand;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

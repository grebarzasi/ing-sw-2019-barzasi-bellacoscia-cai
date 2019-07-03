package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.board.map.NonSpawnSquare;
import it.polimi.ingsw.model.board.map.Room;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.power_up.PowerUp;

import java.util.ArrayList;

/**
 * State of picking an ammo or weapon
 */

public class Picking implements ControllerState{

    /**
     * the amount of movements the player can make before picking
     */
    private int range;

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    Picking(Controller controller) {
        this.controller = controller;
    }

    /**
     * Generates a list of squares the current player can pick items from based on range
     * and sends it to the view, then takes an input and picks the item or redirects to
     * another state according to player's choice
     */

    @Override
    public void executeState() {

        ArrayList<Square> options;

        options = this.controller.canGo(this.controller.getCurrentPlayer(),this.range);
        options.add(this.controller.getCurrentPlayer().getPosition());

        options.removeIf(s -> !s.isSpawn() && (((NonSpawnSquare)s).getDrop() == null) || s.getRoom().getColor().equals(Room.VOID));

        this.controller.checkInactivity();
        Square choice = this.controller.getView().showPossibleMoves(options, false);

        if(choice == null){
            this.controller.goBack();
        }else {

            if (choice.isSpawn()) {

                this.controller.setCurrentState(this.controller.pickingWeapon);
                ((PickingWeapon)this.controller.getPickingWeapon()).setLocation(choice);
                this.controller.update();
                this.controller.getCurrentState().executeState();

            } else {

                this.controller.getCurrentPlayer().setPosition(choice);
                PowerUp check = this.controller.getCurrentPlayer().pickAmmo();

                if(check == null){

                    this.controller.decreaseMoveLeft();
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

    private void choosePU(PowerUp check){

        ArrayList<PowerUp> options = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());
        options.add(check);

        PowerUp chosen = this.controller.getView().showPowerUp(options);


        if (chosen == null&&(controller.getView().isDisconnected()||controller.getView().isInactive())) {
            controller.endTurn();
        } else if(chosen == null){

            chosen = options.get(getRandom(options.size()));
            options.remove(chosen);
            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(chosen);

            this.substitute(options);

        }else {

            options.remove(chosen);

            this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(chosen);
            this.substitute(options);

        }

        this.controller.decreaseMoveLeft();
        this.controller.update();
        this.controller.goBack();

    }

    private void substitute(ArrayList<PowerUp> options ){

        this.controller.getCurrentPlayer().getPowerupList().clear();
        this.controller.getCurrentPlayer().getPowerupList().addAll(options);

    }


    private int getRandom(int max){

        return (int) (Math.random() * ((max) + 1));
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}

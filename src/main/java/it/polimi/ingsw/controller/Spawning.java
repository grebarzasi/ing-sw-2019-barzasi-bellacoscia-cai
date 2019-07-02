package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.View;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

/**
 * controller state corresponding to the spawning phase
 */

public class Spawning implements ControllerState {

    /**
     * the controller reference
     */
    private Controller controller;
    private Player reviving;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    Spawning(Controller controller) {
        this.controller = controller;
    }

    /**
     * Spawning a player
     */

    @Override
    public void executeState() {

        Player tmp = this.controller.getCurrentPlayer();

        this.controller.getModel().setCurrentPlayer(reviving);
        this.controller.setView(this.reviving.getView());

        if (this.controller.getModel().getTurn() >= this.controller.getModel().getPlayerList().size()) {

            ArrayList<PowerUp> options = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());
            options.add((PowerUp) this.controller.getBoard().getPowerupDeck().fetch());

            PowerUp choice = spawnOnChoice(options);
            this.controller.getCurrentPlayer().removePowerUp(choice);

        } else {

            ArrayList<PowerUp> options = new ArrayList<>();

            int i;
            for (i = 0; i < 2; i++) {
                options.add((PowerUp) this.controller.getModel().getBoard().getPowerupDeck().fetch());
            }

            PowerUp used = spawnOnChoice(options);
            this.controller.getCurrentPlayer().getPowerupList().addAll(options);
            this.controller.getCurrentPlayer().removePowerUp(used);

        }

        this.controller.getCurrentPlayer().setDead(false);
        this.controller.update();
        if(controller.getView().isInactive()||controller.getView().isDisconnected())
            this.controller.endTurn();

        this.controller.getModel().setCurrentPlayer(tmp);
        this.controller.setView(tmp.getView());
        this.controller.goBack();

    }

    /**
     * Makes the player choose a powerup and spawns the player on the corresponding spot
     * @param options the list of power up options
     */

    private PowerUp spawnOnChoice(ArrayList<PowerUp> options){


        PowerUp choice;

        choice = this.controller.getView().showPowerUp(options);

        if (choice == null || this.controller.getView().isDisconnected() || this.controller.getView().isInactive()) {
            choice = options.get(options.size()-1);
        }

        options.remove(choice);
        this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(choice);

        Square spawnPoint = powerUptoSpawn(choice);

        this.controller.getCurrentPlayer().setPosition(spawnPoint);

        return choice;


    }


    /**
     * Matches a PowerUp colour to the spawnpoint of that colour
     * @param toMatch the PowerUp to match
     * @return the corresponding spawn point
     */

    private Square powerUptoSpawn(PowerUp toMatch){

        int row;
        int column;

        String colour = "nothing";

        if(toMatch.getAmmoOnDiscard().getRed() == 1){
            colour = "red";
        }else if(toMatch.getAmmoOnDiscard().getBlue() == 1){
            colour = "blue";
        }else if(toMatch.getAmmoOnDiscard().getYellow() == 1){
            colour = "yellow";
        }

        if(colour.equals("nothing")){
            return null;
        }

        for (row = 0; row < Map.HEIGHT; row++) {
            for (column = 0; column < Map.WIDTH; column++) {

                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor().equals(colour) &&
                        this.controller.getBoard().getMap().getSquareMatrix()[row][column].isSpawn()){


                    System.out.println("SPAWNING IN : " + this.controller.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor());
                    return this.controller.getBoard().getMap().getSquareMatrix()[row][column];
                }

            }
        }

        return null;
    }

    public Player getReviving() {
        return reviving;
    }

    public void setReviving(Player reviving) {
        this.reviving = reviving;
    }
}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

/**
 * Controller state corresponding to the spawning phase
 */

public class Spawning implements ControllerState {

    private static final int HEIGHT = 3;
    private static final int WIDTH = 4;


    private Controller controller;

    Spawning(Controller controller) {
        this.controller = controller;
    }

    /**
     * Spawning a player
     */

    @Override
    public void command() {

        if (this.controller.getModel().getTurn() >= this.controller.getModel().getPlayerList().size()) {

            ArrayList<PowerUp> options = new ArrayList<>(this.controller.getCurrentPlayer().getPowerupList());
            options.add((PowerUp) this.controller.getBoard().getPowerupDeck().fetch());

            spawnOnChoice(options);

        } else {

            ArrayList<PowerUp> options = new ArrayList<>();

            int i;
            for (i = 0; i < 2; i++) {
                options.add((PowerUp) this.controller.getModel().getBoard().getPowerupDeck().fetch());
            }

            PowerUp kept = spawnOnChoice(options);
            this.controller.getCurrentPlayer().addPowerUp(kept);

        }

        this.controller.getCurrentPlayer().setDead(false);
        this.controller.update();
        if(controller.getView().isInactive()||controller.getView().isDisconnected())
            this.controller.endTurn();
        this.controller.goBack();

    }

    /**
     * Makes the player choose a powerup and spawns the player on the corresponding spot
     * @param options the list of power up options
     */

    private PowerUp spawnOnChoice(ArrayList<PowerUp> options){

        PowerUp choice;

        choice = this.controller.getView().showPowerUp(options);

        if(choice==null)
            choice=options.get(0);

        options.remove(choice);
        this.controller.getModel().getBoard().getPowerupDeck().getDiscarded().add(choice);

        Square spawnPoint = powerUptoSpawn(choice);

        this.controller.getCurrentPlayer().setPosition(spawnPoint);

        choice = options.get(0);
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

        System.out.println("COLOR IS: " + colour);

        if(colour.equals("nothing")){
            return null;
        }

        for (row = 0; row < HEIGHT; row++) {
            for (column = 0; column < WIDTH; column++) {

                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor().equals(colour) &&
                        this.controller.getBoard().getMap().getSquareMatrix()[row][column].isSpawn()){


                    System.out.println("SPAWNING IN : " + this.controller.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor());
                    return this.controller.getBoard().getMap().getSquareMatrix()[row][column];
                }

            }
        }

        return null;
    }


}

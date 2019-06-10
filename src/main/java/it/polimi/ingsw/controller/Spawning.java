package it.polimi.ingsw.controller;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;

import java.util.ArrayList;

public class Spawning implements ControllerState {

    private static final int height = 3;
    private static final int width = 4;


    private Controller controller;

    public Spawning(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void command() {

        if (!controller.getBoard().getTrack().getKillsTrack().isEmpty()) {

            ArrayList<PowerUp> options = this.controller.getCurrentPlayer().getPowerupList();
            options.add((PowerUp) this.controller.getBoard().getPowerupDeck().fetch());

            spawnOnChoice(options);

        } else {

            ArrayList<Player> players = this.controller.getModel().getPlayerList();

            for (Player p : this.controller.getModel().getPlayerList()) {

                ArrayList<PowerUp> options = new ArrayList<>();

                int i;

                for (i = 0; i < 2; i++) {
                    options.add((PowerUp) this.controller.getModel().getBoard().getPowerupDeck().fetch());
                }

                spawnOnChoice(options);

            }
        }
    }

    private void spawnOnChoice(ArrayList<PowerUp> options){

        PowerUp choice = this.controller.getView().showPowerUp(options);

        options.remove(choice);

        Square spawnPoint = powerUptoSpawn(choice);

        this.controller.getCurrentPlayer().setPosition(spawnPoint);
        this.controller.setCurrentState(this.controller.choosingMove);

    }

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


        if(colour == "nothing"){
            return null;
        }

        for (row = 0; row < height; row++) {
            for (column = 0; column < width; column++) {

                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].getRoom().getColor() == colour ||
                        this.controller.getBoard().getMap().getSquareMatrix()[row][column].isSpawn()){

                    return this.controller.getBoard().getMap().getSquareMatrix()[row][column];
                }

            }
        }

        return null;
    }


}

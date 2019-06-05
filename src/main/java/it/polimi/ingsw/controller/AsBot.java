package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class AsBot implements ControllerState{

    private static final int height = 3;
    private static final int width = 4;

    private Controller controller;

    private boolean hasMoved;
    private boolean hasShot;


    public AsBot(Controller controller) {
        this.controller = controller;

        this.hasMoved = false;
        this.hasShot = false;
    }


    /**
     * Makes the player choose a move and then a target to shoot
     */


    @Override
    public void command() {

        //adds adjacent squares to the bots possible destinations
        ArrayList<Square> canGo = new ArrayList<>();

        int row;
        int column;

        for(row = 0; row < height; row++){
            for(column = 0; column < width; column++){

                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].isAdjacent(this.controller.getModel().getBot().getPosition())){
                    canGo.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
                }
            }
        }


        //makes the player select a destination
        Square botDestination = this.controller.getView().showPossibleMoves(canGo);
        this.controller.getModel().getBot().setPosition(botDestination);


        //adds visible players to the target list
        ArrayList<Figure> targets = new ArrayList<>();

        for(Player p : this.controller.getModel().getPlayerList()){
            if(this.controller.getModel().getBot().canSee(p) && p!= this.controller.getModel().getCurrentPlayer()){
                targets.add(p);
            }
        }


        //shoots the target
        Figure choice = this.controller.getView().singleTargetingShowTarget(targets);

        this.controller.getModel().getBot().shoot((Player)choice);

    }

    public void goBack(){
        this.controller.goBack();
    }

}

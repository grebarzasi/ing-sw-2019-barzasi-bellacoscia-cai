package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;

/**
 * Controller state corresponding to the phase of using a bot
 */

public class AsBot implements ControllerState{

    private static final int HEIGHT = 3;
    private static final int WIDTH = 4;

    private Controller controller;


    public AsBot(Controller controller) {
        this.controller = controller;

    }


    /**
     * Makes the player choose a move and then a target to shoot
     */


    @Override
    public void command() {

        if(this.controller.getModel().getBot().isDead() && this.controller.getModel().getTurn() != 0){

            Square spawnPoint = this.controller.getView().showPossibleMoves(this.returnSpawns(), true);
            this.controller.getModel().getBot().setPosition(spawnPoint);
            this.controller.getModel().getBot().setDead(false);
            this.controller.update();

        }


        ArrayList<Square> canGo = botCanGo();

        //makes the player select a destination
        Square botDestination = this.controller.getView().showPossibleMoves(canGo, false);
        if(botDestination != null) {

            this.controller.getModel().getBot().setPosition(botDestination);
            this.controller.update();

        }else{

            this.goBack();
        }


        //adds visible players to the target list
        ArrayList<Figure> targets = new ArrayList<>();

        for(Player p : this.controller.getModel().getPlayerList()){

            if(this.controller.getModel().getBot().canSee(p) && p!= this.controller.getModel().getCurrentPlayer()){
                targets.add(p);
            }
        }

        if(!targets.isEmpty()) {

            //shoots the target
            Figure choice = this.controller.getView().singleTargetingShowTarget(targets);

            if(choice == null){
                this.botHasMoved();
                this.controller.update();
                this.goBack();
            }else {
                this.controller.getModel().getBot().shoot((Player) choice);
                this.botHasMoved();
                this.controller.update();
                this.goBack();

            }

        }else {

            this.botHasMoved();
            this.controller.update();
            this.goBack();
        }

    }

    private ArrayList<Square> botCanGo(){

        //adds adjacent squares to the bots possible destinations
        ArrayList<Square> canGo = new ArrayList<>();

        int row;
        int column;

        for(row = 0; row < HEIGHT; row++){
            for(column = 0; column < WIDTH; column++){

                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].isAdjacent(this.controller.getModel().getBot().getPosition())){
                    canGo.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
                }
            }
        }

        return canGo;

    }

    public void goBack(){
        this.controller.goBack();
    }

    public ArrayList<Square> returnSpawns(){

        ArrayList<Square> spawns = new ArrayList<>();
        int row;
        int column;

        for(row = 0; row < HEIGHT; row++){
            for(column = 0; column < WIDTH; column ++){
                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].isSpawn())
                    spawns.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
            }
        }

        return spawns;

    }

    public void botHasMoved(){
        this.controller.getModel().setHasBotAction(false);
    }

}

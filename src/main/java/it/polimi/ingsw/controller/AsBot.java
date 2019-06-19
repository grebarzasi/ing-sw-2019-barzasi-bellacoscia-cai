package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.map.Map;
import it.polimi.ingsw.board.map.Square;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller state corresponding to the phase of using a bot
 */

public class AsBot implements ControllerState{




    private Controller controller;


    AsBot(Controller controller) {
        this.controller = controller;

    }


    /**
     * Makes the player choose a move and then a target to shoot
     */


    @Override
    public void command() {

        //Checks if it's the first turn before spawning the bot
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
            Set<Figure> temp=new HashSet<>(targets);
            //shoots the target
            ArrayList<Figure> choice = this.controller.getView().showTargetAdvanced(temp,1,false,"Seleziona un bersaglio da colpire:");

            if(choice == null){
                this.botHasMoved();
                this.controller.update();
                this.goBack();
            }else {
                this.controller.getModel().getBot().shoot((Player) choice.get(0));
                this.botHasMoved();
                this.controller.update();

                //asks the target if he wants to use a tagback grenade
                Set<Figure> hitTarget = new HashSet<>(choice);
                this.controller.askVenoms(hitTarget,this.controller.getModel().getBot());

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

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH; column++){

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

    private ArrayList<Square> returnSpawns(){

        ArrayList<Square> spawns = new ArrayList<>();
        int row;
        int column;

        for(row = 0; row < Map.HEIGHT; row++){
            for(column = 0; column < Map.WIDTH; column ++){
                if(this.controller.getBoard().getMap().getSquareMatrix()[row][column].isSpawn())
                    spawns.add(this.controller.getBoard().getMap().getSquareMatrix()[row][column]);
            }
        }

        return spawns;

    }

    private void botHasMoved(){
        this.controller.getModel().setHasBotAction(false);
    }

}

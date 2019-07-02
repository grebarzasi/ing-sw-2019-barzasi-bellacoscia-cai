package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.KillshotTrack;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import static it.polimi.ingsw.controller.ControllerMessages.GAME_OVER;

/**
 * controller state of the endgame status
 */

public class EndGame implements ControllerState {

    /**
     * the controller reference
     */
    private Controller controller;

    /**
     * Default constructor
     * @param controller the reference controller
     */
    public EndGame(Controller controller) {
        this.controller = controller;
    }

    /**
     * Displayes the leaderboard to all the players
     */

    @Override
    public void executeState() {

        for(Player p: this.controller.getModel().getPlayerList()){
            p.getView().displayLeaderboard(this.generateLeaderBoard());
        }

        System.out.println(GAME_OVER);
        System.exit(0);


    }

    public ArrayList<Figure> generateLeaderBoard(){

        ArrayList<Figure> allPlayers = new ArrayList<>();
        allPlayers.addAll(this.controller.getModel().getPlayerList());
        allPlayers.add(this.controller.getModel().getBot());

        ArrayList<Figure> leaderBoard = new ArrayList<>();

        for(Figure f : allPlayers){
            int i;
            boolean added = false;

            if(leaderBoard.isEmpty()){
                leaderBoard.add(f);
            }else {
                for (i = 0; i < leaderBoard.size(); i++) {
                    if (f.getPoints() >= leaderBoard.get(i).getPoints() && added == false){
                        leaderBoard.add(i,f);
                        added = true;
                    }
                }
                if(added == false){
                    leaderBoard.add(f);
                }

            }
        }

        HashMap<Figure,Integer> killsPerPlayer = this.controller.getModel().getBoard().getTrack().mapKillsPerPlayer();

        int i;
        int k;

        for(i = 0 ; i< leaderBoard.size() ; i++){
            for(k = 0 ; k < leaderBoard.size() ; k++){

                //if the checked players' kills is not zero
                if(killsPerPlayer.get(leaderBoard.get(i)) != null && killsPerPlayer.get(leaderBoard.get(k)) != null
                        &&
                        leaderBoard.get(i).getPoints() == leaderBoard.get(k).getPoints() && killsPerPlayer.get(leaderBoard.get(k)) > killsPerPlayer.get(leaderBoard.get(i))){

                    Figure temp = leaderBoard.get(i);
                    leaderBoard.set(i,leaderBoard.get(k));
                    leaderBoard.set(k,temp);

                }
            }
        }

        return leaderBoard;
    }


}

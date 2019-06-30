package it.polimi.ingsw.controller;

import it.polimi.ingsw.Figure;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.board.KillshotTrack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Controller state of the endgame status
 */

public class EndGame implements ControllerState {

    private Controller controller;

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
            p.getView().displayMessage("GAME IS FUCKING OVER BOYS");

        }

        System.out.println("GAME IS FUCKING OVER BOYS");


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

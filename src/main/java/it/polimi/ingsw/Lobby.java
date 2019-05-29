package it.polimi.ingsw;


import it.polimi.ingsw.connection.socket.ClientThreadSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby {

    private boolean hasStarted;
    private Timer timer;


    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<ClientThreadSocket> joinedPlayers;


    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
        this.timer= new Timer();
        this.hasStarted=false;
    }

    public synchronized boolean addPlayer(ClientThreadSocket p) {
        if (this.joinedPlayers.size() < maxPlayer && usernameCheck(p) && characterCheck(p)) {
            this.joinedPlayers.add(p);
            return true;
        }
        return false;
    }

    /**
     * Updates all client when other player is added
     */
    public synchronized void updateClients() {

        int i = 0;
        for (ClientThreadSocket c : joinedPlayers) {
            if (c.isReady()) {
                i++;
                c.updateLobby();
            }
        }
        if (i > 2) {
            System.out.println("countdown started");
            timer.schedule(new TimerGameStart(this), 20 * 1000);
        }
    }

    /**
     * Updates all client when game starts
     */


    public boolean usernameCheck(ClientThreadSocket p){
        for(ClientThreadSocket toCheck: this.joinedPlayers){
            if(toCheck.getOwner().getUsername().equals(p.getOwner().getUsername())){
                return false;
            }
        }
        return true;
    }

    public boolean characterCheck(ClientThreadSocket p){
        for(ClientThreadSocket toCheck: this.joinedPlayers){
            if(toCheck.getOwner().getCharacter().equals(p.getOwner().getCharacter())){
                return false;
            }
        }
        return true;
    }

    public void disconnectPlayer(ClientThreadSocket p){
        this.joinedPlayers.remove(p);
        System.out.print(p.getOwner().getUsername() + " has cowardly left the battle before it began\n");
    }

    /**
     * Checks if all players have set their status to ready
     * @return True if all players are ready, false otherwise
     */
    public boolean allReady(){
        for (ClientThreadSocket p : joinedPlayers){
            if(!p.isReady()){
                return false;
            }
        }
        return true;
    }

    public boolean canStart(){
        if(joinedPlayers.size()>=3 && allReady()){
            return true;
        }
        return false;
    }


    public ArrayList<ClientThreadSocket> getJoinedPlayers() {
        return joinedPlayers;
    }

    public void setJoinedPlayers(ArrayList<ClientThreadSocket> joinedPlayers) {
        this.joinedPlayers = joinedPlayers;
    }

    public synchronized boolean hasStarted() {
        return hasStarted;
    }

    public synchronized void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    @Override
    public synchronized String toString() {
        String s="";
        for(ClientThreadSocket c: joinedPlayers){
            s= s + c.toString()+";";
        }
        return s;
    }
}









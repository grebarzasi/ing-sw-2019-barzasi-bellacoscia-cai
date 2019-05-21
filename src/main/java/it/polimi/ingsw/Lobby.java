package it.polimi.ingsw;


import it.polimi.ingsw.connection.socket.ClientThreadSocket;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby {

    private boolean gameStarted=false;

    public boolean isGameStarted() {
        return gameStarted;
    }

    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<ClientThreadSocket> joinedPlayers;
    //Maps each player to their status, ready or not
    private HashMap<ClientThreadSocket,Boolean> readyStatus = new HashMap<>();


    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
        this.readyStatus = new HashMap<>();
    }

    public Lobby(ClientThreadSocket p){
        this.joinedPlayers = new ArrayList<>();
        this.joinedPlayers.add(p);
        this.readyStatus.put(p,false);

    }

    public boolean addPlayer(ClientThreadSocket p){
        if (this.joinedPlayers.size() < maxPlayer && usernameCheck(p) && characterCheck(p)) {
            this.joinedPlayers.add(p);
            this.readyStatus.put(p, false);
            return true;
        }
        return false;
    }

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
        this.readyStatus.remove(p);
        System.out.print(p.getOwner().getUsername() + " has cowardly left the battle before it began\n");
    }

    /**
     * Checks if all players have set their status to ready
     * @return True if all players are ready, false otherwise
     */
    public boolean allReady(){

        for (ClientThreadSocket p : joinedPlayers){
            if(!readyStatus.get(p)){
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

    public void readyPlayer(ClientThreadSocket p){
        this.readyStatus.replace(p,true);

    }

    public void unreadyPlayer(ClientThreadSocket p){
        this.readyStatus.replace(p,false);
    }

    public ArrayList<ClientThreadSocket> getJoinedPlayers() {
        return joinedPlayers;
    }

    public void setJoinedPlayers(ArrayList<ClientThreadSocket> joinedPlayers) {
        this.joinedPlayers = joinedPlayers;
    }

    public HashMap<ClientThreadSocket, Boolean> getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(HashMap<ClientThreadSocket, Boolean> readyStatus) {
        this.readyStatus = readyStatus;
    }
}









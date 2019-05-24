package it.polimi.ingsw;


import it.polimi.ingsw.connection.socket.ClientThreadSocket;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby {

    private boolean hasStarted =false;

    public boolean hasStarted() {
        return hasStarted;
    }


    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<ClientThreadSocket> joinedPlayers;


    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
    }

    public boolean addPlayer(ClientThreadSocket p) {
        if (this.joinedPlayers.size() < maxPlayer && usernameCheck(p) && characterCheck(p)) {
            this.joinedPlayers.add(p);
            updateClient();
            return true;
        }
        return false;
    }

    /**
     * Updates all client when other player is added
     */
    public void updateClient(){
        for(ClientThreadSocket c : joinedPlayers){
            if(c.isWaiting())
                c.updateLobby();
        }
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

    @Override
    public String toString() {
        String s="";
        for(ClientThreadSocket c: joinedPlayers){
            s= s + c.toString()+";";
        }
        return s;
    }
}









package it.polimi.ingsw;


import it.polimi.ingsw.connection.socket.ClientThreadSocket;

import java.util.ArrayList;
import java.util.Timer;


/**
 * The game lobby, contains up to 5 players and their ready status
 */

public class Lobby {

    private boolean hasStarted;
    private boolean hasTimerStarted;
    private Timer timer;
    private DisconnectChecker disconnectChecker;
    private final int DELAY=30;


    private final int maxPlayer = 5;

    //List of players who have joined the lobby
    private ArrayList<ClientThreadSocket> joinedPlayers;


    public Lobby() {
        this.joinedPlayers = new ArrayList<>();
        this.timer= new Timer();
        this.hasStarted=false;
        this.hasTimerStarted=false;
        this.disconnectChecker = new DisconnectChecker(this);
    }

    public synchronized boolean addPlayer(ClientThreadSocket p) {
        if (joinedPlayers.size() < maxPlayer && usernameCheck(p) && characterCheck(p)) {
            joinedPlayers.add(p);
            if(!disconnectChecker.isAlive())
                disconnectChecker.start();
            return true;
        }

        return false;
    }

    /**
     * Restore session on connection start
     */

    public synchronized boolean restorePlayer(ClientThreadSocket p) {
        if (!usernameCheck(p)) {
            for(ClientThreadSocket toCheck: this.joinedPlayers){
                if(toCheck.getOwner().getUsername().equals(p.getOwner().getUsername())){
                   if(toCheck.isExpired())
                       return false;
                    return true;
                }
            }

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
        if (i == 3 && !hasStarted) {
            hasTimerStarted=true;
            System.out.println("Countdown started");
            timer.schedule(new TimerGameStart(this), (DELAY * 1000));
        }
    }



    public boolean usernameCheck(ClientThreadSocket p){
        for(ClientThreadSocket toCheck: joinedPlayers){
            if(toCheck.getOwner().getUsername().equals(p.getOwner().getUsername())){
                return false;
            }
        }
        return true;
    }

    public boolean characterCheck(ClientThreadSocket p){
        for(ClientThreadSocket toCheck: joinedPlayers){
            if(toCheck.getOwner().getCharacter().equals(p.getOwner().getCharacter())){
                return false;
            }
        }
        return true;
    }

    public void disconnectPlayer(ClientThreadSocket p){
        joinedPlayers.remove(p);
        updateClients();
        System.out.print(p.getOwner().getUsername() + " has cowardly left the battle before it began\n");
    }



    public synchronized ArrayList<ClientThreadSocket> getJoinedPlayers() {
        return joinedPlayers;
    }

    public void setJoinedPlayers(ArrayList<ClientThreadSocket> joinedPlayers) {
        this.joinedPlayers = joinedPlayers;
    }

    public synchronized boolean hasStarted() {
        return hasStarted;
    }

    public synchronized boolean hasTimerStarted() {
        return hasTimerStarted;
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









package it.polimi.ingsw.connection.rmi;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.connection.ClientHandler;

import java.io.IOException;
import java.rmi.RemoteException;

public class RmiClientHandler extends ClientHandler implements RmiPrefInterf {

    public RmiCInterf client;
    public String tempPlayer;

    public RmiClientHandler(Lobby lobby, RmiCInterf client){
        super(lobby);
        this.client=client;
    }

    public boolean login(String username, String character) throws RemoteException{
            super.setOwner(new Player(username,character));
            if (super.getLobby().addPlayer(this)) {
                System.out.println("repling to " + super.getOwner().getUsername() + ": accepted!");
                return true;
            }
            System.out.println("repling to " + username + ": refused!");
            return false;
    }
    public void sendPref(Integer mapPref, Integer killPref, Boolean terminatorPref,Boolean finalFrenzyPref) throws RemoteException{
        super.setMapPref(mapPref);
        super.setKillPref(killPref);
        super.setTerminatorPref(terminatorPref);
        super.setFinalFrenzyPref(finalFrenzyPref);
        System.out.println(super.getOwner().getUsername() + " pref registered\n");
        super.setReady(true);
        super.getLobby().updateClients();
    }

    /**
     * sends updates to client
     */
    @Override
    public void updateLobby() { setTempPlayer(super.getLobby().toString());

    }

    public String waitUpdate() { return getTempPlayer();
    }

    public boolean isGameStarted() { return super.getLobby().hasStarted();
    }
    public boolean isTimerStarted() { return super.getLobby().hasTimerStarted();
    }

    /**
     * Wait for ready status
     */
    @Override
    public void waitStart() throws IOException {

    }

    @Override
    public void game() {

    }

    @Override
    public void run() {

    }

    public RmiCInterf getClient() {
        return client;
    }

    public void setClient(RmiCInterf client) {
        this.client = client;
    }

    public synchronized String getTempPlayer() {
        return tempPlayer;
    }

    public synchronized void setTempPlayer(String tempPlayer) {
        this.tempPlayer = tempPlayer;
    }
}

package it.polimi.ingsw.connection.rmi;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.ServerCommManager;
import it.polimi.ingsw.virtual_model.ViewClient;

import java.io.IOException;
import java.rmi.RemoteException;

import static it.polimi.ingsw.connection.ServerMessage.THREAD_PRIORITY;

/**
 * the client handler "handle" the communication with client. in this case ones using rmi technology
 *
 * @author Gregorio Barzasi
 */
public class RmiClientHandler extends ClientHandler implements RmiPrefInterf {


    public String tempPlayer;
    public ViewClient viewClient;

    public RmiClientHandler(Lobby lobby){
        super(lobby);
    }


    /**
     * receives the login info and reply to client the result of login.
     */
    public String login(String username, String character) throws RemoteException{
            super.setOwner(new Player(username,character));

            if(super.getLobby().hasStarted()&&super.getLobby().reconnectPlayer(this))
                return "reconnected";


            if (super.getLobby().addPlayer(this)) {
                System.out.println("repling to " + super.getOwner().getUsername() + ": accepted!");
                return "accepted";
            }
            System.out.println("repling to " + username + ": refused!");
            return "refused";
    }

    /**
     * receives preferences from client and add it to lobby
     */
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
    public void waitStart()throws IOException {
        System.out.println("Waiting for game start");
        try {
        while (!super.getLobby().hasTimerStarted()){
                sleep(500);
        }
        while (!super.getLobby().hasStarted()){
            sleep(500);
        }
        } catch (InterruptedException e) {
        e.printStackTrace();
    }

    }

    @Override
    public void game(){
        while(viewClient==null) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.getOwner().setView(new ServerCommManager(this));
        ((ServerCommManager) super.getOwner().getView()).setPriority(THREAD_PRIORITY);
        ((ServerCommManager) super.getOwner().getView()).start();

    }

    @Override
    public void run() {
        try {
            if(super.getLobby().hasStarted())
                return;
            waitStart();
            game();
        } catch (IOException e) {
        e.printStackTrace();
    }
    }
    //reset
    public void setView(ViewClient view) {
       setViewClient(view);
    }

    public boolean isRmi(){
        return true;
    }

    public ViewClient getViewClient() {
        return viewClient;
    }

    public void setViewClient(ViewClient viewClient) {
        this.viewClient = viewClient;
    }

    public synchronized String getTempPlayer() {
        return tempPlayer;
    }

    public synchronized void setTempPlayer(String tempPlayer) {
        this.tempPlayer = tempPlayer;
    }
}

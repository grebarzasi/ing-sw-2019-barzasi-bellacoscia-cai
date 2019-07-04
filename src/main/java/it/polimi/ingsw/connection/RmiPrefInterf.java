package it.polimi.ingsw.connection;

import it.polimi.ingsw.ViewClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * interface used by clients to call remote server methods
 * @author Gregorio Barzasi
 */
public interface RmiPrefInterf extends Remote {
    public String login(String username, String color) throws RemoteException;
    public void sendPref(Integer mapPref, Integer killPref, Boolean terminatorPref,Boolean finalFrenzyPref) throws RemoteException;
    public String waitUpdate() throws RemoteException;

    public void setView(ViewClient view) throws RemoteException;

    public boolean isTimerStarted() throws RemoteException;
    public boolean isGameStarted() throws RemoteException;
}

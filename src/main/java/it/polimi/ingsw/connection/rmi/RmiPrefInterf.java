package it.polimi.ingsw.connection.rmi;

import it.polimi.ingsw.virtual_model.ViewClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
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

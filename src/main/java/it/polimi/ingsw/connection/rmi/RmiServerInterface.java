package it.polimi.ingsw.connection.rmi;

import it.polimi.ingsw.virtual_model.ViewClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiServerInterface extends Remote {
    public RmiPrefInterf getClientHandler()throws RemoteException;
}

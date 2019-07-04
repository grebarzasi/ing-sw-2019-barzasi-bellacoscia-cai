package it.polimi.ingsw.connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiServerInterface extends Remote {
    public RmiPrefInterf getClientHandler()throws RemoteException;
}

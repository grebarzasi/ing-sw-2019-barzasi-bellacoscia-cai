package it.polimi.ingsw.connection.server;

import it.polimi.ingsw.connection.client.rmi.RmiPrefInterf;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiServerInterface extends Remote {
    public RmiPrefInterf getClientHandler()throws RemoteException;
}

package it.polimi.ingsw.Connection.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiCInterf extends Remote {
    boolean isConnected() throws RemoteException;
}

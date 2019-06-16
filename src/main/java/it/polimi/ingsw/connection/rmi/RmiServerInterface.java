package it.polimi.ingsw.connection.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiServerInterface extends Remote {
    public RmiPrefInterf getClientHandler(RmiCInterf temp)throws RemoteException;
}

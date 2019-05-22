package it.polimi.ingsw.connection.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiSInterf extends Remote {
    boolean login(String username, String color) throws RemoteException;
    RmiCInterf sendClient(RmiCInterf p)throws RemoteException;
}

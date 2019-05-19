package it.polimi.ingsw.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Gregorio Barzasi
 */
public interface RmiInterface extends Remote {
    String hello() throws RemoteException;
    String login(String username, String color) throws RemoteException;
}

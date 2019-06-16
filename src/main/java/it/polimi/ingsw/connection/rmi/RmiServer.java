package it.polimi.ingsw.connection.rmi;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.Lobby;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.Thread.sleep;

/**
 * @author Gregorio Barzasi
 */
public class RmiServer extends ConnectionTech implements RmiServerInterface{


    public RmiServer(Lobby lobby){
        super(lobby);
    }


    public RmiPrefInterf getClientHandler(RmiCInterf c)throws RemoteException{
        RmiPrefInterf temp=new RmiClientHandler(super.getLobby(),c);
        return(RmiPrefInterf) UnicastRemoteObject.exportObject(temp, 0);
    }

    public void run(){

        try {
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(super.getPort());

            RmiServerInterface stub = (RmiServerInterface) UnicastRemoteObject.exportObject(this, super.getPort());
            registry.bind("Server", stub);
            System.err.println("RMI Server ready");

        } catch (Exception e) {
            System.err.println("RMI Server exception");
            e.printStackTrace();
        }
    }

}
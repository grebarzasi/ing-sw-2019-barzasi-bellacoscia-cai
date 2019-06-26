package it.polimi.ingsw.connection.rmi;

import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.virtual_model.ViewClient;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static it.polimi.ingsw.connection.ServerMessage.*;
import static java.lang.Thread.sleep;

/**
 * @author Gregorio Barzasi
 */
public class RmiServer extends ConnectionTech implements RmiServerInterface{


    public RmiServer(Lobby lobby){
        super(lobby);
    }


    public RmiPrefInterf getClientHandler()throws RemoteException{
        RmiPrefInterf temp=new RmiClientHandler(super.getLobby());
        ((ClientHandler)temp).setPriority(MIN_PRIORITY);
        ((ClientHandler)temp).start();
        return(RmiPrefInterf) UnicastRemoteObject.exportObject(temp, 0);
    }

    public void run(){

        try {
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(super.getPort());

            RmiServerInterface stub = (RmiServerInterface) UnicastRemoteObject.exportObject(this, super.getPort());
            registry.bind("Server", stub);
            System.out.println(RMI_S_READY+super.getPort());

        } catch (Exception e) {
            System.out.println(RMI_S_ERR);
            e.printStackTrace();
        }
    }

}
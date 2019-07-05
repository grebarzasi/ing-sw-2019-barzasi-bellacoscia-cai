package it.polimi.ingsw.connection.server;

import it.polimi.ingsw.connection.RmiServerInterface;
import it.polimi.ingsw.connection.RmiPrefInterf;
import it.polimi.ingsw.controller.client_handler.ClientHandler;
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.controller.Lobby;
import it.polimi.ingsw.connection.DisconnectionHandler;
import it.polimi.ingsw.controller.client_handler.RmiClientHandler;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static it.polimi.ingsw.connection.server.ServerMessage.*;
import static java.lang.Thread.sleep;

/**
 * Server side communication class. Rmi technology.
 * @author Gregorio Barzasi
 */
public class RmiServer extends ConnectionTech implements RmiServerInterface {

    private Lobby lobby;


    public RmiServer(Lobby lobby){
        this.lobby=lobby;    }

    /**
     * after connection clients use this method to obtain {@link RmiPrefInterf} reference.
     */
    public RmiPrefInterf getClientHandler()throws RemoteException{
        RmiPrefInterf temp=new RmiClientHandler(lobby,super.getCountdown());
        ((ClientHandler)temp).setPriority(THREAD_PRIORITY);
        ((ClientHandler)temp).start();
        return(RmiPrefInterf) UnicastRemoteObject.exportObject(temp, 0);
    }

    public boolean connected(){return true;};

    @Override
    public void run(){

        try {
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(super.getPort());

            RmiServerInterface stub = (RmiServerInterface) UnicastRemoteObject.exportObject(this, super.getPort());
            registry.bind("Server", stub);
            System.out.println(RMI_S_READY+super.getPort());

        } catch (Exception e) {
            DisconnectionHandler.server(true);
        }
    }

}
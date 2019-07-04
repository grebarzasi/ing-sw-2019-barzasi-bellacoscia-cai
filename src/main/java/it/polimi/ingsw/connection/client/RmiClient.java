package it.polimi.ingsw.connection.client;

/**
 * Client-side Rmi connection
 *
 * @author Gregorio Barzasi
 */
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.DisconnectionHandler;
import it.polimi.ingsw.connection.RmiPrefInterf;
import it.polimi.ingsw.connection.RmiServerInterface;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public class RmiClient extends ConnectionTech implements Remote {

    private RmiPrefInterf clientHandler;
    private boolean connected=false;


    public void run() {
        try {
            Registry serverRegistry = LocateRegistry.getRegistry(super.getIp(),super.getPort());
            RmiServerInterface server = (RmiServerInterface) serverRegistry.lookup("Server");
            clientHandler = server.getClientHandler();
            System.out.println(CONNECTION_OK);
            connected=true;
        } catch (Exception e) {
            DisconnectionHandler.client(true);
        }
    }

    public boolean connected(){return connected;};

    public RmiPrefInterf getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(RmiPrefInterf clientHandler) {
        this.clientHandler = clientHandler;
    }
}


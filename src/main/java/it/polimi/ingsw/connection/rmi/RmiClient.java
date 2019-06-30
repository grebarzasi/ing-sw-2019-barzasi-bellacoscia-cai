package it.polimi.ingsw.connection.rmi;

/**
 * Client-side Rmi connection
 *
 * @author Gregorio Barzasi
 */
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.DisconnectionHandler;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static it.polimi.ingsw.CLI.CliMessages.CONNECTION_OK;

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


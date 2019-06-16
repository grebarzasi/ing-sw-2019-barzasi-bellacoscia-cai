package it.polimi.ingsw.connection.rmi;

/**
 * @author Gregorio Barzasi
 */
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.virtual_model.ViewClient;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient extends ConnectionTech implements RmiCInterf{

    private RmiPrefInterf clientHandler;


    public void run() {
        try {
            Registry serverRegistry = LocateRegistry.getRegistry(super.getIp(),super.getPort());
            RmiServerInterface server = (RmiServerInterface) serverRegistry.lookup("Server");
            RmiCInterf client = (RmiCInterf) UnicastRemoteObject.exportObject(this,0);
            clientHandler = server.getClientHandler(client);
            System.out.println("RMI connection established\n");
        } catch (Exception e) {
            System.err.println("RMI connection error\n");
            e.printStackTrace();

        }
    }

    public RmiPrefInterf getClientHandler() {
        return clientHandler;
    }

    public void setClientHandler(RmiPrefInterf clientHandler) {
        this.clientHandler = clientHandler;
    }
}


package it.polimi.ingsw.Connection.rmi;

/**
 * @author Gregorio Barzasi
 */
import it.polimi.ingsw.Connection.ConnectionTech;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient extends ConnectionTech implements RmiCInterf {

    private RmiSInterf server;



    public boolean isConnected(){
        System.out.println("connected");
        return true;
    }

    public void initConnection() {
        try {
            Registry registry = LocateRegistry.getRegistry(super.getIp(),super.getPort());
            server = (RmiSInterf) registry.lookup("Server");
            RmiCInterf client = (RmiCInterf) UnicastRemoteObject.exportObject(this, 0);
            server.sendClient(client);
            System.out.println("RMI Connection established\n");
        } catch (Exception e) {
            System.err.println("RMI Connection error\n");
            e.printStackTrace();
        }

    }


    public RmiSInterf getServerRmi() {
        return server;
    }
}


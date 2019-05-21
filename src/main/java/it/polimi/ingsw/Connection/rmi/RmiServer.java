package it.polimi.ingsw.Connection.rmi;

import it.polimi.ingsw.Connection.ConnectionTech;
import it.polimi.ingsw.Lobby;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.Thread.sleep;

/**
 * @author Gregorio Barzasi
 */
public class RmiServer extends ConnectionTech implements RmiSInterf {

    private Lobby lobby;
    public RmiCInterf client;

    public String login(String username, String color) throws RemoteException {
        System.out.println("logged " + username +" "+ color);
        return username + color;
    }

    public RmiCInterf sendClient(RmiCInterf temp)throws RemoteException{
        this.client=temp;
        System.out.println(client.isConnected());
        return temp;
    }

    public void initConnection() {
        try {
            super.getPort();
            RmiSInterf stub = (RmiSInterf) UnicastRemoteObject.exportObject(this, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(super.getPort());
            registry.bind("Server", stub);
            System.err.println("RMI Server ready");

        } catch (Exception e) {
            System.err.println("RMI Server exception");
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        new RmiServer().initConnection();

    }
}

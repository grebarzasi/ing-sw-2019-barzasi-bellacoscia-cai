package it.polimi.ingsw.Connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * @author Gregorio Barzasi
 */
public class RmiServer extends Connection implements RmiInterface {


    public RmiServer(){
        super();
    };

    public String hello() throws RemoteException {
        System.out.println("porta " + port);
        return port + "";
    }

    public String login(String username, String color) throws RemoteException {
        System.out.println("logged " + username +" "+ color);
        return username + color;
    }



    public static void main(String args[]){

        try {
            RmiServer obj = new RmiServer();
            obj.acquirePort();
            RmiInterface stub = (RmiInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(obj.port);
            registry.bind("login", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }




    }
}

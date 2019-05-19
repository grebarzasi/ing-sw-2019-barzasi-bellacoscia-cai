package it.polimi.ingsw.rmi;

import it.polimi.ingsw.Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 * @author Gregorio Barzasi
 */
public class RmiServer implements RmiInterface {

    private int port;



    public RmiServer(){};

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
            obj.setPort(acquirePort());
            RmiInterface stub = (RmiInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(obj.getPort());
            registry.bind("login", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }




    }

    public static int acquirePort() {
        Scanner sc = new Scanner(System.in);
        int port;
        System.out.println("Insert port:");
        do {
            port = sc.nextInt();
            if(port <= 1023 || port > 49151){
                System.out.println("Not available port, insert another port:");
            }
        }while(port <= 1023 || port > 49151);
        return port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

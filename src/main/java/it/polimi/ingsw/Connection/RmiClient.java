package it.polimi.ingsw.Connection;

/**
 * @author Gregorio Barzasi
 */
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiClient extends Connection {


    private RmiClient() {
    }

    private void login(RmiInterface stub) throws RemoteException {

        this.acquireUsername();
        this.acquireCharacter();

        System.out.println("Welcome "+ username + "!\n");

        stub.login(username,character);
    }

    public static void main(String[] args) {

        try {
            RmiClient obj = new RmiClient();
            obj.acquirePort();
            Registry registry = LocateRegistry.getRegistry(obj.port);
            RmiInterface stub = (RmiInterface) registry.lookup("login");

            //login
            obj.login(stub);

            String response = stub.hello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

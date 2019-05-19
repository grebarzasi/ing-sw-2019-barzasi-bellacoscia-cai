package it.polimi.ingsw.rmi;

/**
 * @author Gregorio Barzasi
 */
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiClient {

    private int port;

    private RmiClient() {
    }

    private void login(RmiInterface stub) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert username");
        String username = sc.nextLine();
        System.out.println("Insert color");
        String character = null;
        do{
            character = sc.nextLine();
            if(!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray")){
                System.out.println("Not available color, insert another color:");
            }
        }while(!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray"));

        System.out.println("Welcome "+ username + "!\n");

        stub.login(username,character);
    }

    public static void main(String[] args) {

        try {
            RmiClient obj = new RmiClient();
            obj.setPort(RmiServer.acquirePort());
            Registry registry = LocateRegistry.getRegistry(obj.getPort());
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
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

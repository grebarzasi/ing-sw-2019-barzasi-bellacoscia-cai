package it.polimi.ingsw.model_buffer.CLI;

import it.polimi.ingsw.Connection.ConnectionTech;
import it.polimi.ingsw.Connection.SClient;
import it.polimi.ingsw.Connection.rmi.RmiClient;
import it.polimi.ingsw.model_buffer.LoginBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class CliView {
    private ConnectionTech c;
    private BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String args[])throws RemoteException,IOException {
        CliView cliView = new CliView();
        System.out.println("Benvenuto in adrenalina! seleziona la connessione da utilizzare:\n");
        cliView.chooseConnection();
        cliView.login().send();
    }



    public void chooseConnection() throws IOException {
        System.out.println("1-RMI\n2-Socket\nSelect connection type:\n");
        int temp = Integer.parseInt(sc.readLine());
        if(temp==1){
            c= new RmiClient();
            c.setRmi(true);
            System.out.println("Rmi\n");
        }else if(temp==2){
            c= new SClient();
            c.setRmi(false);
            System.out.println("Socket\n");

        }
        //c.setPort(this.acquirePort());
        c.initConnection();
    }
    public LoginBuffer login()throws IOException{
        System.out.println("It's time to login!");
        String username = acquireUsername();
        String character = acquireCharacter();
        return new LoginBuffer(username,character,c);
    }


    public void setConnection(ConnectionTech c){
        this.c=c;
    }
    public ConnectionTech getConnection(){
       return this.c;
    }

    public int acquirePort()throws IOException {
        int port;
        System.out.println("Insert port:");
        do {
            port = Integer.parseInt(sc.readLine());
            if (port <= 1023 || port > 49151) {
                System.out.println("Not available port, insert another port:");
            }
        } while (port <= 1023 || port > 49151);
        return port;
    }

    private String acquireUsername()throws IOException{
            System.out.println("Insert username");
            return sc.readLine();
        }


    private String acquireCharacter()throws IOException{
            String character;
            do {
                System.out.println("Insert color");
                character = sc.readLine();
                if (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray")) {
                    System.out.println("Not available color, insert another color:");
                }
            } while (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray"));
    return character;
    }
}

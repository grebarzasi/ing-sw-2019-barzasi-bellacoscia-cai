package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.rmi.RmiClient;
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
        cliView.login();
    }



    public void chooseConnection() throws IOException {
        System.out.println("1-RMI\n2-socket\nSelect connection type:\n");
        int temp = Integer.parseInt(sc.readLine());
        if(temp==1){
            c= new RmiClient();
            c.setRmi(true);
            System.out.println("Rmi\n");
        }else if(temp==2){
            c= new SClient();
            c.setRmi(false);
            System.out.println("socket\n");

        }
        //c.setPort(this.acquirePort());
        c.initConnection();
    }
    public void login()throws IOException{
        System.out.println("It's time to login!");
        LoginBuffer l;
        while(true){
            String username = acquireUsername();
            String character = acquireCharacter();
            l = new LoginBuffer(username, character, c);
            System.out.println("waiting...");
            if(l.send()){
                System.out.println("Login success!");
                break;
            }
            System.out.println("Login failed! name or character already in use");
        }
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

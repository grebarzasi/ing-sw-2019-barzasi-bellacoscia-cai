package it.polimi.ingsw.CLI;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.rmi.RmiClient;
import it.polimi.ingsw.virtual_model.VirtualLobby;
import it.polimi.ingsw.virtual_model.VirtualLogin;
import it.polimi.ingsw.virtual_model.VirtualPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CliView {

    private ConnectionTech c;
    private BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    private VirtualPlayer p;
    private CliLobby lobby;

    private String ip;
    private int port;


    public static void main(String args[]) {
        CliView cliView = new CliView();
        cliView.start();
    }


    public void start(){
        System.out.println("------------------------------\n" +
                "*** Welcome to ADRENALINA! ***"+
                "\n------------------------------");
        try {
            chooseConnection();
            login();
            lobby = new CliLobby(c,sc,p);
            lobby.startLobby();
        }catch(Exception e){
            System.err.println("Connection error");
            e.printStackTrace();
        }
    }


    /**
     * command connection type
     */


    public void chooseConnection() throws IOException {
        System.out.println("\nSelect connection type: (RMI/SOCKET)");
        String temp = sc.readLine();
        if(temp.equals("R")||temp.equals("RMI")||temp.equals("1")||temp.equals("r")){
            c= new RmiClient();
            c.setRmi(true);
            System.out.println("\nRMI Selected");
        }else if(temp.equals("S")||temp.equals("Socket")||temp.equals("2")||temp.equals("s")){
            c= new SClient();
            c.setRmi(false);
            System.out.println("\nSocket Selected");

        }
        port=acquirePort();
        ip=acquireIp();
        if(port!=0)
            c.setPort(port);
        if(!ip.isEmpty())
            c.setIp(ip);
        c.initConnection();
    }

    /**
     * parse login info
     */

    public void login()throws IOException{
        System.out.println("\nIt's time to login!");
        VirtualLogin l;
        while(true){
            String username = acquireUsername();
            String character = acquireCharacter();
            l = new VirtualLogin(username, character, c);
            System.out.println("waiting...");
            if(l.send()){
                System.out.println("Login success!");
                p = new VirtualPlayer(username,character);
                break;
            }
            System.out.println("\nLogin failed! name or character already in use, try again!");
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
        String s = sc.readLine();
        if(s.isEmpty())
            return 0;
        do {
            port = Integer.parseInt(s);
            if (port <= 1023 || port > 49151) {
                System.out.println("Not available port, insert another port:");
            }
        } while (port <= 1023 || port > 49151);
        return port;
    }

    public String acquireIp()throws IOException{
        System.out.println("\nInsert IP:");
        return sc.readLine();
    }

    /**
     * parse username
     */
    private String acquireUsername()throws IOException{
            System.out.println("\n__Insert Username:");
            return sc.readLine();
        }


    /**
     * parse character
     */
    private String acquireCharacter()throws IOException{
            String character;
            do {
                System.out.println("\n__Insert color");
                character = sc.readLine();
                if (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray")) {
                    System.out.println("Not available color, insert another color:");
                }
            } while (!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray"));
    return character;
    }
}

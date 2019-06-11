package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.CLI.CliView;
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.MainServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Client side Socket connection
 *
 * @author Gregorio Barzasi
 */
public class SServer extends ConnectionTech {

    private MainServer mainServer;


    public SServer(MainServer mainServer){
        this.mainServer=mainServer;
    }
    /**
     * Initialize connection and wait for client to connect
     */
    public void run() {

        CliView cli = new CliView();
        System.out.print("SOCKET SERVER");
        try {
            int port=cli.acquirePort();
            String ip =cli.acquireIp();
            if(port!=0)
                setPort(port);
            if(!ip.isEmpty())
                setIp(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ServerSocket server = new ServerSocket(super.getPort());

            System.out.println("Server started  " + super.getIp() + " : "+super.getPort());

            //loops until game start waiting for other players

            SClientHandler temp;
            while(true){
                Socket client = server.accept();
                System.out.println("\n\nconnection established with\n" + client);
                temp = new SClientHandler(client,mainServer.getLobby());
                temp.start();
            }



        } catch (Exception e) {
            System.err.println("socket connection error\n");
            e.printStackTrace();
        }

    }

    public void game() {
        System.out.println("*SERVER IN GAME*");
    }
}
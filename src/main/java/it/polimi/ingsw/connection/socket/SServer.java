package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.CLI.CliView;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.ConnectionTech;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Client side Socket connection
 *
 * @author Gregorio Barzasi
 */
public class SServer extends ConnectionTech {

    private Lobby lobby=new Lobby();

    /**
     * Initialize connection and wait for client to connect
     */
    public void initConnection() throws RemoteException {
        try {
            ServerSocket server = new ServerSocket(super.getPort());

            System.out.println("Server started  " + super.getIp() + " : "+super.getPort());

            //loops until game start waiting for other players

            ClientThreadSocket temp;
            while(!lobby.hasStarted()){
                Socket client = server.accept();
                System.out.println("\n\nconnection established with\n" + client);
                temp = new ClientThreadSocket(client,lobby);
                temp.start();
            }



        } catch (Exception e) {
            System.err.println("socket connection error\n");
            e.printStackTrace();
            throw new RemoteException();
        }

    }



    public static void main(String[] args){
        SServer s = new SServer();
        CliView cli = new CliView();

        try {
            int port=cli.acquirePort();
            String ip =cli.acquireIp();
            if(port!=0)
                s.setPort(port);
            if(!ip.isEmpty())
                s.setIp(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.initConnection();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
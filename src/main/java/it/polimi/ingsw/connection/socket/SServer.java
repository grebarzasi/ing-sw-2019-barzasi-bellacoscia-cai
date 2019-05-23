package it.polimi.ingsw.connection.socket;

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
    private Socket client;
    private ServerSocket server;
    private ArrayList<ClientThreadSocket> players;

    ClientThreadSocket temp;


    /**
     * Initialize connection and wait for client to connect
     */
    public void initConnection() throws RemoteException {
        try {
            server = new ServerSocket(super.getPort());

            System.out.println("Server started");

            //loops until game start waiting for other players

            while(!lobby.isGameStarted()){
                client = server.accept();
                System.out.println("connection established with\n" + client);
                temp = new ClientThreadSocket(client,lobby);
                players.add(temp);
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
        try {
            s.initConnection();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
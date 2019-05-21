package it.polimi.ingsw.Connection;

import it.polimi.ingsw.Connection.rmi.RmiServer;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket connection.
 *
 * @author Carlo Bellacoscia
 */
public class SServer extends ConnectionTech {
    private Lobby lobby=new Lobby();
    private Socket client;
    private ServerSocket server;

    public void initConnection() {
        try {
            server = new ServerSocket(super.getPort());
            System.out.println("Server started");
            System.out.println("Server is listening on port " + super.getPort());

            while(!lobby.isGameStarted()){
                client = server.accept();
                new ClientThread(client).start();
                System.out.println("Connection established with\n" + client);
            }

        } catch (Exception e) {
            System.err.println("Socket Connection error\n");
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        new SServer().initConnection();
    }
}
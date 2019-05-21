package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.ConnectionTech;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket connection.
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
                new ClientThreadSocket(client,lobby).start();
                System.out.println("connection established with\n" + client);
            }

        } catch (Exception e) {
            System.err.println("socket connection error\n");
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        new SServer().initConnection();
    }
}
package it.polimi.ingsw.connection.server;

import it.polimi.ingsw.controller.Lobby;
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.DisconnectionHandler;
import it.polimi.ingsw.controller.client_handler.SocketClientHandler;

import java.net.ServerSocket;
import java.net.Socket;

import static it.polimi.ingsw.Color.RESET;
import static it.polimi.ingsw.connection.server.ServerMessage.*;

/**
 * Client side Socket connection
 *
 * @author Gregorio Barzasi
 */
public class SServer extends ConnectionTech {

    private Lobby lobby;

    public SServer(Lobby lobby){
    this.lobby=lobby;
    }
    /**
     * Initialize connection and wait for client to connect
     */
    public void run() {
        try {
            ServerSocket server = new ServerSocket(super.getPort());

            System.out.println(SOCKET_S_READY +" : "+super.getPort());
            System.out.print(RESET);
            //loops until game start waiting for other players

            SocketClientHandler temp;
            while(true){
                Socket client = server.accept();
                System.out.println( "\n"+SOCKET_S_CONN+ client);
                temp = new SocketClientHandler(client,lobby,super.getCountdown());
                temp.setPriority(THREAD_PRIORITY);
                temp.start();
            }



        } catch (Exception e) {
            DisconnectionHandler.server(false);

        }

    }
    public boolean connected(){return true;};

}
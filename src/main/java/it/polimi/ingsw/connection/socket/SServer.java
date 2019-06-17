package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.CLI.CliView;
import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.MainServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static it.polimi.ingsw.connection.ServerMessage.*;

/**
 * Client side Socket connection
 *
 * @author Gregorio Barzasi
 */
public class SServer extends ConnectionTech {



    public SServer(Lobby lobby){
        super(lobby);
    }
    /**
     * Initialize connection and wait for client to connect
     */
    public void run() {
        try {
            ServerSocket server = new ServerSocket(super.getPort());

            System.out.println(SOCKET_S_READY + super.getIp() + " : "+super.getPort());

            //loops until game start waiting for other players

            SocketClientHandler temp;
            while(true){
                Socket client = server.accept();
                System.out.println( SOCKET_S_CONN+ client);
                temp = new SocketClientHandler(client,super.getLobby());
                temp.start();
            }



        } catch (Exception e) {
            System.err.println(SOCKET_S_ERR);
            e.printStackTrace();
        }

    }

//    public void game() {
//        System.out.println("*SERVER IN GAME*");
//    }
}
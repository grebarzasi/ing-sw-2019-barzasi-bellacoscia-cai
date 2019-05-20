package it.polimi.ingsw.Connection;

import java.io.*;
import java.net.*;

/**
 * The type Client.
 *
 * @author Carlo Bellacoscia
 */
public class SClient extends Connection{


    private Socket socketClient = null;


    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Connect socket.
     *
     * @return the socket
     */
    public Socket connect(SClient c){

        try {

            System.out.println("Try to connect...");


            socketClient = new Socket("127.0.0.1",port);
            System.out.println("Connection established\n");

            /**
             *prompt for username and character
             */

            c.acquireUsername();
            c.acquireCharacter();


            System.out.println("Welcome "+ username + "!\n");
            PrintWriter output = new PrintWriter(socketClient.getOutputStream(), true);

            /**
             * send username and character to server
             */
            output.println(username);
            output.println(character);
            output.flush();

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Impossible to establish the connection\n");

        }
        return socketClient;
    }


    public static void main( String[] args ) {

        SClient c = new SClient();
        c.acquirePort();
        c.connect(c);
    }
}

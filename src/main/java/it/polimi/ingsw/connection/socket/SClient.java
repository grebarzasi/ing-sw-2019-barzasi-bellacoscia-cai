package it.polimi.ingsw.connection.socket;


import it.polimi.ingsw.connection.ConnectionTech;

import java.io.*;
import java.net.Socket;

/**
 * The type Client.
 *
 * @author Carlo Bellacoscia
 */
public class SClient extends ConnectionTech {

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    public void initConnection() {
        try {

            System.out.println("Try to connect...");
            server = new Socket(super.getIp(), super.getPort());
            this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            this.out = new PrintWriter(server.getOutputStream(),true);
            System.out.println("connection established\n");
        } catch (Exception e) {
            System.err.println("connection error\n");
        }
    }

    public Socket getServer() {
        return server;
    }

    public PrintWriter getOutput() {
        return out;
    }
    public BufferedReader getInput() {
        return in;
    }
}

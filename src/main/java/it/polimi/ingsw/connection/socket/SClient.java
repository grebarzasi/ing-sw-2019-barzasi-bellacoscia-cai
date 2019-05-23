package it.polimi.ingsw.connection.socket;


import it.polimi.ingsw.connection.ConnectionTech;

import java.io.*;
import java.net.Socket;

/**
 * Client side Socket connection
 *
 * @author Gregorio Barzasi
 */
public class SClient extends ConnectionTech {

    private Socket server;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Initialize connection and input and output streams
     */
    public void initConnection() {
        try {
            System.out.println("Try to connect...");

            this.server = new Socket(super.getIp(), super.getPort());
            System.out.println("connection established\n");
            this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())));
        } catch (Exception e) {
            System.err.println("connection error\n");
            e.printStackTrace();
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


    public static void main(String[] args){
        SClient c = new SClient();
        c.initConnection();
        c.getOutput().println("test");
    }
}

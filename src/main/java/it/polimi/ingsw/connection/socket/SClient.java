package it.polimi.ingsw.connection.socket;


import it.polimi.ingsw.connection.ConnectionTech;

import java.io.*;
import java.net.Socket;
import java.rmi.RemoteException;

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
    public void initConnection() throws IOException{
            System.out.println("Connecting");

            this.server = new Socket(super.getIp(), super.getPort());
            System.out.println("Connection established\n");
            this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())),true);
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

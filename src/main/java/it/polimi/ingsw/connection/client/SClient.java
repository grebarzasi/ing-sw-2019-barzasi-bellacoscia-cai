package it.polimi.ingsw.connection.client;


import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.DisconnectionHandler;

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
    private SClientCommManager commManager;
    private boolean connected=false;

    /**
     * Initialize connection and input and output streams
     */

        public void run() {
            try {
                this.server = new Socket(super.getIp(), super.getPort());
                connected=true;
                System.out.println(CONNECTION_OK);
                this.in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())), true);
            }catch(IOException e){
                DisconnectionHandler.client(false);
            }
        }

    public Socket getServer() {
        return server;
    }

    public boolean connected(){return connected;};

    public PrintWriter getOutput() {
        return out;
    }
    public BufferedReader getInput() {
        return in;
    }

    public SClientCommManager getCommManager() {
        return commManager;
    }

    public void setCommManager(SClientCommManager commManager) {
        this.commManager = commManager;
    }
}

package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Client thread
 *
 * @author Carlo Bellacoscia
 */
public class ClientThreadSocket extends Thread {

    private Lobby lobby;
    private Player owner;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThreadSocket(Socket s, Lobby lobby) throws IOException{
        this.lobby=lobby;
        this.client = s;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
    }

    public void waitLogin()throws IOException {
        while(in.readLine()!="login");
        String username=in.readLine();
        String character=in.readLine();
        owner = new Player(username,character);
        lobby.addPlayer(this);
    }

    public void run()  {
        try {
            waitLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Player getOwner() {
        return owner;
    }
}

package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A thread for each client that handle the communication using Socket tech
 * @author Gregorio Barzasi
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
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())));
        this.owner = new Player();
    }
    /**
     * Login procedure
     */
    public boolean waitLogin()throws IOException {
        System.out.println("Waiting login");
        String temp;
        do{
           temp = in.readLine();
        }
        while(temp!="login");
        owner.setUsername(in.readLine());
        owner.setCharacter(in.readLine());
        out.println("reply");
        System.out.println("reply");
        if(lobby.addPlayer(this)){
            out.println("accepted");
            return true;
        }else{
            out.println(owner.getUsername()+ " refused");
            return false;
        }
    }

    public void run() {
        System.out.println("Thread started");
        try {
            echo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getOwner() {
        return owner;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void echo() throws IOException {
        String line = in.readLine();
            System.out.println(line);
    }
}

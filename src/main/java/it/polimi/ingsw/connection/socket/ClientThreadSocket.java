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
        this.out = new PrintWriter(client.getOutputStream(),true);
        this.owner = new Player();
    }

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

    public void run()  {
        try {
            System.out.println("Thread started");
            while(waitLogin());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Player getOwner() {
        return owner;
    }
}

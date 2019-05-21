package it.polimi.ingsw.Connection;

import it.polimi.ingsw.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Client thread
 *
 * @author Carlo Bellacoscia
 */
public class ClientThread extends Thread {

    private ArrayList<String> playerConnected = new ArrayList<>();
    private String username;
    private String character;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public ClientThread(Socket s) throws IOException{
        this.playerConnected.add(username);
        this.client = s;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
    }

    public void waitLogin()throws IOException {
        while(in.readLine()!="login");
        username=in.readLine();
        character=in.readLine();
        System.out.println(username + " logged as:"+ character);
        out.println(username + "logged as:"+ character);
    }

    public void run()  {
        try {
            waitLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

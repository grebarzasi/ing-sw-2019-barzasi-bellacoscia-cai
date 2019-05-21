package it.polimi.ingsw.Connection;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket connection.
 *
 * @author Carlo Bellacoscia
 */
public class SServer extends Connection{

    private Socket client = null;

    private Lobby lobby = new Lobby();

    public SServer() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }


    public Socket connection(){

        try {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {

                    this.acquirePort();

                    System.out.println("Server started");


                    System.out.println("Server is listening on port " + port);
                    client = serverSocket.accept();

                    System.out.println("Connection established\n");



                    logInfo();
                    Player p = new Player(username, character);

                    ClientThread handler = new ClientThread(p,serverSocket);
                    handler.start();

                    lobby.addPlayer(p);

                    System.out.println("Welcome, " + username + "!\n");
                    System.out.println("Players in game:");
                    for (Player t : lobby.getJoinedPlayers()) {
                        System.out.println(t.getUsername() + " (" + t.getCharacter() + ")");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public void logInfo(){

        /**
         * open buffered reader for reading data from client
         */
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            /**
             * read username and character
             */
            username = in.readLine();
            System.out.println("username: " + username);

            character = in.readLine();
            System.out.println("character: " + character + "\n");

            /**
             * open printwriter for writing data to client
             */
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);


            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nConnection not work");
        }
    }

    public static void main(String[] args){

        SServer s = new SServer();

        s.connection();

    }

}

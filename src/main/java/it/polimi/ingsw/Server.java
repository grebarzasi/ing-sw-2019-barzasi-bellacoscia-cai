package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Socket connection.
 *
 * @author Carlo Bellacoscia
 */
public class Server {

    private Socket client = null;

    private Lobby lobby = new Lobby();

    private int port;
    String username;
    String character;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }


    public Socket connection(){

        port = getPort();

        try {
            System.out.println("Server started");
            try (ServerSocket socketServer = new ServerSocket(port)) {
                while (socketServer.isBound()) {
                        System.out.println("Server is listening on port " + port);
                        client = socketServer.accept();

                        System.out.println("Connection established\n");



                        logInfo();
                        Player p = new Player(username, character);

                        ClientHandler handler = new ClientHandler(p);
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

        Scanner sc = new Scanner(System.in);
        Server s = new Server();
        int port;

        System.out.println("Insert port:");
        do {
            port = sc.nextInt();
            s.setPort(port);
            if(port <= 1023 || port > 49151){
                System.out.println("Not available port, insert another port:");
            }
        }while(port <= 1023 || port > 49151);

        s.connection();

    }

}

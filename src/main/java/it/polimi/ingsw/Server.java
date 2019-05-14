package it.polimi.ingsw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private ServerSocket server = null;
    private Socket client = null;

    int port;
    private BufferedReader input;
    private PrintWriter output;
    String username;
    String character;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Socket connection.
     */
    public Socket connection(){

        port = getPort();

        try {

            System.out.println("Inizializzo Server");
            server = new ServerSocket(port);

            System.out.println("Server in ascolto sulla porta " + port);
            client = server.accept();

            System.out.println("Connessione stabilita\n");

            try {

                logInfo();
                Player p = new Player(username, character);

            } catch (Exception e) {
                e.printStackTrace();
            }

            server.close();

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
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));

            /**
             * read username and character
             */
            username = input.readLine();
            System.out.println("username: " + username);

            character = input.readLine();
            System.out.println("character: " + character + "\n");

            /**
             * open printwriter for writing data to client
             */
            output = new PrintWriter(client.getOutputStream(), true);


            System.out.println("Benvenuto, " + username + "!\n");

            output.flush();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\nConnessione non riuscita");
        }
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Server s = new Server();

        System.out.println("Inserisci la porta");
        s.setPort(sc.nextInt());
        s.connection();
    }

}

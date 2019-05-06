package it.polimi.ingsw;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The type Game controller client.
 *
 * @author Carlo Bellacoscia
 */
public class GameControllerClient {

    private Socket client = null;

    int door = 1984;

    private BufferedReader input;
    private PrintWriter output;


    /**
     * Connect socket.
     *
     * @return the socket
     */
    public Socket connect(){

        try {

            System.out.println("Tentativo connessione...");
            Socket client = new Socket(InetAddress.getLocalHost(),door);
            System.out.println("Connessione stabilita\n");

            /**
             *prompt for username and character
             */
            Scanner sc = new Scanner(System.in);
            System.out.println("Inserisci username");
            //String username = JOptionPane.showInputDialog(null, "Inserisci username:");
            String username = sc.nextLine();

            System.out.println("Inserisci il tuo colore");
            //String character = JOptionPane.showInputDialog(null, "Inserisci il tuo colore:");
            String character = sc.nextLine();


            System.out.println("Benvenuto "+ username + "!\n");
            input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(client.getOutputStream(), true);

            /**
             * send username and character to server
             */
            output.println(username);
            output.println(character);
            output.flush();

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Impossibile stabilire la connessione\n");

        }
        return client;
    }

    public static void main( String[] args ) {

        GameControllerClient c = new GameControllerClient();
        c.connect();
    }
}

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

    private final int maxPlayers = 5;

    private Socket client = null;


    int port;

    private BufferedReader input;
    private PrintWriter output;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Connect socket.
     *
     * @return the socket
     */
    public Socket connect(){

        try {

            System.out.println("Tentativo connessione...");


            /*
            **************************************************************
            //BLOCKER ISSUE MATE
            **************************************************************
            */

            Socket client = new Socket(InetAddress.getLocalHost(),port);
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

        Scanner sc = new Scanner(System.in);
        GameControllerClient c = new GameControllerClient();

        System.out.println("Inserisci la porta");
        c.setPort(sc.nextInt());
        c.connect();
    }
}

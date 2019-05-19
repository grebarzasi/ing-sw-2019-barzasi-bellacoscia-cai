package it.polimi.ingsw;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The type Client.
 *
 * @author Carlo Bellacoscia
 */
public class Client{


    private Socket socketClient = null;


    int port;

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

            System.out.println("Try to connect...");


            socketClient = new Socket("127.0.0.1",port);
            System.out.println("Connection established\n");

            /**
             *prompt for username and character
             */
            Scanner sc = new Scanner(System.in);
            System.out.println("Insert username");
            String username = sc.nextLine();


            System.out.println("Insert color");
            String character = null;
            do{
                character = sc.nextLine();
                if(!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray")){
                    System.out.println("Not available color, insert another color:");
                }
            }while(!character.equals("blue") && !character.equals("red") && !character.equals("yellow") && !character.equals("green") && !character.equals("gray"));



                System.out.println("Welcome "+ username + "!\n");
            PrintWriter output = new PrintWriter(socketClient.getOutputStream(), true);

            /**
             * send username and character to server
             */
            output.println(username);
            output.println(character);
            output.flush();

        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Impossible to establish the connection\n");

        }
        return socketClient;
    }


    public static void main( String[] args ) {

        Scanner sc = new Scanner(System.in);
        Client c = new Client();
        int port;

        System.out.println("Insert port:");
        do {
            port = sc.nextInt();
            c.setPort(port);
            if(port <= 1023 || port > 49151){
                System.out.println("Not available port, insert another port:");
            }
        }while(port <= 1023 || port > 49151);
        c.connect();
    }
}

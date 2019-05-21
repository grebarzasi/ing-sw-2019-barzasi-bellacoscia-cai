package it.polimi.ingsw.Connection;

import it.polimi.ingsw.javaFX.LoginJavaFX;

import java.io.*;
import java.net.*;

/**
 * The type Client.
 *
 * @author Carlo Bellacoscia
 */
public class SClient extends Connection{


    private Socket socketClient = null;

    private LoginJavaFX login = new LoginJavaFX();


    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Connect socket.
     *
     * @return the socket
     */
    public void connect(){

        try {

            System.out.println("Try to connect...");


            socketClient = new Socket("127.0.0.1",port);
            System.out.println("Connection established\n");



        } catch (Exception e) {

            e.printStackTrace();
            System.err.println("Impossible to establish the connection\n");

        }
    }

    public void login(){
        /**
         *prompt for username and character
         */
        if(login.getUsername() == null){
            this.acquireUsername();
        }else {
            username = login.getUsername();
        }

        if(login.getColor() == null){
            this.acquireCharacter();
        }else{
            character = login.getColor();
        }


        System.out.println("Welcome "+ username + "!\n");
        PrintWriter output = null;
        try {
            output = new PrintWriter(socketClient.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * send username and character to server
         */
        output.println(username);
        output.println(character);
        output.flush();
    }

    public static void main( String[] args ) {

        SClient c = new SClient();
        c.acquirePort();
        c.connect();
    }
}

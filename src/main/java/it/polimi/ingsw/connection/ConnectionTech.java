package it.polimi.ingsw.connection;

import it.polimi.ingsw.controller.Lobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import static it.polimi.ingsw.Color.PURPLE;
import static it.polimi.ingsw.Color.RESET;


/**
        * Conatins basic info to connect a client and a server using Rmi or Socket technology
        * @author Gregorio Barzasi
        */

public abstract class ConnectionTech extends Thread {

    public static final String CONNECTION_OK = "Connessione stabilita\n"+RESET;
    public static final String PORT_ERR = "Porta non disponibile, cambiala!"+RESET;
    public static final String PORT_SELECT = PURPLE+"Inserisci la porta: (lascia vuoto per i valori di default)"+RESET;



    /**
     * port number for connection, default: 1234
     */

    private int port=1234;
    /**
     * ip address of connection, default: 127.0.0.1
     */
    private String ip = "127.0.0.1";

    /**
     * indicates that the connection used is rmi. default is socket
     */
    private boolean rmi=false;
    /**
     * saves the inactivity countdown used in game
     */
    private int countdown;

    public ConnectionTech(){
    }

    /**
     * acquire the port for connection
     */
    public void acquirePort(){
        try {
            int port=readPort();
            if(port!=0)
                setPort(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read port for connection from terminal
     */
    public int readPort()throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        int port;
        System.out.println("\n"+PORT_SELECT);

        do {
            String s = sc.readLine();
            if(s.isEmpty()){
                return 0;
            }
            try {
                port = Integer.parseInt(s);
            }catch(NumberFormatException e){
                port=0;
            }
            if (port <= 1023 || port > 49151) {
                System.out.println(PORT_ERR);
            }
        } while (port <= 1023 || port > 49151);
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setRmi(boolean rmi) {
        this.rmi = rmi;
    }

    public boolean isRmi(){
        return rmi;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public abstract void run();
    public abstract boolean connected();

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
}

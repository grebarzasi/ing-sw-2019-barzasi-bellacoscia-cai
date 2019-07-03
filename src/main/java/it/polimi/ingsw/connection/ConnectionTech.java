package it.polimi.ingsw.connection;

import it.polimi.ingsw.CLI.CliView;
import it.polimi.ingsw.Lobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static it.polimi.ingsw.CLI.CliMessages.PORT_ERR;
import static it.polimi.ingsw.CLI.CliMessages.PORT_SELECT;

/**
        * Conatins basic info to connect a client and a server using Rmi or Socket technology
        * @author Gregorio Barzasi
        */

public abstract class ConnectionTech extends Thread {

    private Lobby lobby;
    //default 127.0.0.1:1234
    private int port=1234;
    private String ip = "127.0.0.1";

    //default is rmi
    private boolean rmi=false;

    public ConnectionTech(Lobby lobby){
        this.lobby=lobby;
    }
    public ConnectionTech(){
    }


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
     * acquire port for connection
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

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}

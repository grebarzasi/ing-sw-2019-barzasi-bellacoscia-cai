package it.polimi.ingsw.connection;

import it.polimi.ingsw.CLI.CliView;
import it.polimi.ingsw.Lobby;

import java.io.IOException;

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

    public void acquireIP(){
        CliView cli = new CliView();
        try {
            String ip =cli.acquireIp();
            if(!ip.isEmpty())
                setIp(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void acquirePort(){
        CliView cli = new CliView();
        try {
            int port=cli.acquirePort();
            if(port!=0)
                setPort(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

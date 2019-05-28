package it.polimi.ingsw.connection;

import java.rmi.RemoteException;

public abstract class ConnectionTech {

    //default 127.0.0.1:1234
    private int port=1234;
    public String ip = "192.168.43.211";

    //default is rmi
    private boolean rmi=false;

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

    public abstract void  initConnection()throws RemoteException;
}

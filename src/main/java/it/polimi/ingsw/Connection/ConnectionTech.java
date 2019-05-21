package it.polimi.ingsw.Connection;

public abstract class ConnectionTech {

    //default 127.0.0.1:1234
    private int port=1234;
    public String ip="127.0.0.1";

    //default is rmi
    private boolean rmi=true;

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

    public abstract void  initConnection();
}

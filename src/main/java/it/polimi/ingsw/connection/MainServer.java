package it.polimi.ingsw.connection;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.rmi.RmiServer;
import it.polimi.ingsw.connection.socket.SServer;
import it.polimi.ingsw.controller.Controller;




public class MainServer {

    private SServer socketServer;
    private RmiServer rmiServer;
    private Lobby lobby;
    private Controller contr;

    public MainServer(){
        this.socketServer=new SServer(this);
        //this.rmiServer=new RmiServer(this);
        this.lobby=new Lobby(this);
    }

    public static void main(String[] args){
        MainServer god = new MainServer();
        god.startAll();
    }

    public void startAll() {
            lobby.start();
            socketServer.start();
            //rmiServer.initConnection();

    }

    public void startGame() {
        contr= new Controller(lobby);
        contr.update();
        contr.setCurrentState(contr.getSpawning());
        contr.getCurrentState().command();
    }


    public SServer getSocketServer() {
        return socketServer;
    }

    public void setSocketServer(SServer socketServer) {
        this.socketServer = socketServer;
    }

    public RmiServer getRmiServer() {
        return rmiServer;
    }

    public void setRmiServer(RmiServer rmiServer) {
        this.rmiServer = rmiServer;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

}

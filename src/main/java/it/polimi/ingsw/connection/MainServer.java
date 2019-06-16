package it.polimi.ingsw.connection;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.rmi.RmiServer;
import it.polimi.ingsw.connection.socket.SServer;
import it.polimi.ingsw.controller.Controller;

import static it.polimi.ingsw.CLI.CliColor.GREEN;
import static it.polimi.ingsw.CLI.CliColor.YELLOW;


public class MainServer {

    private SServer socketServer;
    private RmiServer rmiServer;
    private Lobby lobby;
    private Controller contr;

    public MainServer(){
        this.lobby=new Lobby(this);
        this.socketServer=new SServer(lobby);
        this.rmiServer=new RmiServer(lobby);
    }

    public static void main(String[] args){
        MainServer god = new MainServer();
        god.startAll();
    }

    public void startAll() {
        lobby.start();
        System.out.print(YELLOW+"RMI SERVER");
        rmiServer.setPort(1235);
        rmiServer.acquireConnInfo();
        rmiServer.run();
        System.out.print(GREEN+"SOCKET SERVER");
        socketServer.acquireConnInfo();
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

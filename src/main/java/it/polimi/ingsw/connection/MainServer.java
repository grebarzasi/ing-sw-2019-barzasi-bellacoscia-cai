package it.polimi.ingsw.connection;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.rmi.RmiServer;
import it.polimi.ingsw.connection.socket.SServer;
import it.polimi.ingsw.controller.Controller;

import java.io.IOException;

import static it.polimi.ingsw.CLI.CliMessages.LINE_SEP;
import static it.polimi.ingsw.connection.ServerMessage.*;
import static java.lang.Thread.sleep;


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
        System.out.println(SERVER_HEAD);
        System.out.println(RMI_MSG);
        rmiServer.setPort(1235);
        System.out.print("DEFAULT PORT: 1235");
        rmiServer.acquirePort();
        rmiServer.run();
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(LINE_SEP);
        System.out.println(SOCKET_MSG);
//        System.out.print("DEFAULT IP: 127.0.0.1");
//        socketServer.acquireIP();
        System.out.print("DEFAULT PORT: 1234");
        socketServer.acquirePort();
        socketServer.start();
        //rmiServer.initConnection();

    }

    public void startGame()throws IOException {
        contr= new Controller(lobby);
        contr.update();
        contr.setCurrentState(contr.getSpawning());
        contr.getCurrentState().executeState();
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

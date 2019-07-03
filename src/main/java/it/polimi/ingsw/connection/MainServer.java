package it.polimi.ingsw.connection;


import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.connection.rmi.RmiServer;
import it.polimi.ingsw.connection.socket.SServer;
import it.polimi.ingsw.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import static it.polimi.ingsw.connection.ServerMessage.*;



/**
 * Class that initializes rmi and socket servers in order to handle connection from both simultaneously
 * @author Gregorio Barzasi
 */


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
        lobby.setPriority(THREAD_PRIORITY);
        lobby.start();
        System.out.println(SERVER_HEAD);
        try {
            lobby.setTestMode(testMode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(RMI_MSG);
        rmiServer.setPort(1235);
        System.out.print("DEFAULT PORT: 1235");
        rmiServer.acquirePort();
        rmiServer.run();

        System.out.print(LINE_SEP);
        System.out.println(SOCKET_MSG);
        System.out.print("DEFAULT PORT: 1234");
        socketServer.acquirePort();
        socketServer.setPriority(THREAD_PRIORITY);
        socketServer.start();


    }

    public void startGame()throws IOException {
        contr= new Controller(lobby);
        contr.update();
        contr.setCurrentState(contr.getChoosingMove());
        contr.getCurrentState().executeState();
    }

    /**
     * advanced setting
     */
    public boolean testMode()throws IOException{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String temp;
        do {
            System.out.println(TEST_MODE);
            temp = sc.readLine();
            temp=temp.toLowerCase();
            if (temp.equals("s")||temp.equals("y")||temp.equals("1")) {
                System.out.println("ATTIVATA!");
                return true;
            } else if (temp.equals("n")||temp.equals("2")||temp.isEmpty()) {
                System.out.println("DISATTIVATA!");
                return false;
            }else{
                System.err.println(GENERIC_N);
            }
        }while(true);

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

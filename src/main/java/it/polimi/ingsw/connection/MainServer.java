package it.polimi.ingsw.connection;



import it.polimi.ingsw.controller.Lobby;
import it.polimi.ingsw.connection.server.RmiServer;
import it.polimi.ingsw.connection.server.SServer;
import it.polimi.ingsw.controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;


import static it.polimi.ingsw.Color.*;
import static it.polimi.ingsw.connection.server.ServerMessage.*;
import static it.polimi.ingsw.connection.server.ServerMessage.GENERIC_N;
import static it.polimi.ingsw.connection.server.ServerMessage.LINE_SEP;


/**
 * Class that initializes rmi and socket servers in order to handle connection from both simultaneously
 * @author Gregorio Barzasi
 */


public class MainServer {
    /**
     * read input from terminal
     */
   private BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    /**
     * saves the inactivity countdown, default is 30 s
     */
    private int countdown=30;
    /**
     * saves the countdown, default is 30 s
     */
    private int lobbyCountdown=30;
    /**
     * the server of socket connection
     */
    private SServer socketServer;
    /**
     * the server of rmi connection
     */
    private RmiServer rmiServer;

    /**
     *game lobby, it accept players
     */
    private Lobby lobby;

    public MainServer(){
        this.lobby=new Lobby(this);
        this.socketServer=new SServer(lobby);
        this.rmiServer=new RmiServer(lobby);
    }
    /**
     * Main, entry point for server
     */

    public static void main(String[] args){
        MainServer god = new MainServer();
        god.startAll();
    }
    /**
     * run all servers and setup connection an advanced mode
     */
    public void startAll() {
        lobby.setPriority(THREAD_PRIORITY);
        lobby.start();
        System.out.println(SERVER_HEAD);
        try {
            advanced();
        } catch (IOException e) {
            e.printStackTrace();
        }

        rmiServer.setCountdown(countdown);
        socketServer.setCountdown(countdown);

        try {
            System.out.println(GREEN+"\nYOUR IP: "+InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
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
        System.out.print(RESET);

    }

    /**
     * initialize controller and start the game
     */
    public void startGame() {
        Controller contr = new Controller(lobby);
        contr.update();
        contr.setCurrentState(contr.getChoosingMove());
        contr.getCurrentState().executeState();
    }

    /**
     * advanced setting
     */
    public void advanced()throws IOException{
        if(!ynAsk(ADV_MODE))
            return;
        lobby.setTestMode(ynAsk(TEST_MODE));
        countdown=askTimer(ASK_TIMER_I);
        lobbyCountdown=askTimer(ASK_TIMER_L);

    }
    /**
     * you can set your own countdown timer
     */
    public int askTimer(String msg)throws IOException{
        String temp;
        int num=0;
        do {System.out.println(msg);
            temp = sc.readLine();
            if(temp.isEmpty())
                temp="30";
            try{
                num = Integer.parseInt(temp);
            }
            catch (NumberFormatException e)
            {num=0;
            }
            if(num<1){
                System.err.println(GENERIC_N);
            }
        }while(num<1);
        return num;
    }

    /**
     * generic question with y or no answer
     */
    public boolean ynAsk(String q)throws IOException{
        String temp;
        do {
            System.out.println(q);
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



    public Lobby getLobby() {
        return lobby;
    }

    public int getLobbyCountdown() {
        return lobbyCountdown;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

}

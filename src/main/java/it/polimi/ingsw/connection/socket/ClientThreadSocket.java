package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * A thread for each client that handle the communication using Socket tech
 * @author Gregorio Barzasi
 */
public class ClientThreadSocket extends Thread {

    private Lobby lobby;
    private Player owner;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean ready=false;
    private boolean expired=false;

    private GameManagerSocket manager;

    //preferences
    private int mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;

    public ClientThreadSocket(Socket s, Lobby lobby) throws IOException{
        this.lobby=lobby;
        this.client = s;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
        this.owner = new Player();
    }

    public ClientThreadSocket(Lobby lobby) throws IOException{
        this.lobby=lobby;
        this.owner = new Player();
    }

    /**
     * Login procedure: read user and character, if lobby accepts them reply to client "accepted" .
     * if lobby refuse the login reply "refused" and keep waiting for login until a valid login is achieved.
     */
    public boolean waitLogin()throws IOException {
        do {
            System.out.println("Waiting login");
            owner.setUsername(in.readLine());
            owner.setCharacter(in.readLine());
            if (lobby.addPlayer(this)) {
                out.println("accepted");
                System.out.println("repling to " + owner.getUsername() + ": accepted!");
                break;
            } else {
                out.println("refused");
                System.out.println("repling to " + owner.getUsername() + ": refused!");
            }
        }while(true);
        return true;
    }

    /**
     * Login procedure: read user and character, if lobby accepts them reply to client "accepted" .
     * if lobby refuse the login reply "refused" and keep waiting for login until a valid login is achieved.
     */
    public boolean waitLoginGS()throws IOException {
        do {
            System.out.println("Waiting login GS");
            owner.setUsername(in.readLine());
            owner.setCharacter(in.readLine());
            if (lobby.restorePlayer(this)) {
                out.println("accepted");
                System.out.println("repling to " + owner.getUsername() + ": restored!");
                break;
            } else {
                out.println("refused");
                System.out.println("repling to " + owner.getUsername() + ": refused!");
            }
        }while(true);
        return true;
    }

    /**
     * Wait for game settings from client.
     */


    public void waitPref()throws IOException {
        System.out.println("Waiting pref for "+ owner.getUsername());
        mapPref=Integer.parseInt(in.readLine());
        killPref=Integer.parseInt(in.readLine());
        terminatorPref=Boolean.parseBoolean(in.readLine());
        finalFrenzyPref=Boolean.parseBoolean(in.readLine());
        out.println("accepted");
        System.out.println(owner.getUsername() + " pref registered\n");
        ready=true;
        lobby.updateClients();
    }

    /**
     * sends updates to client
     */
    public void updateLobby(){
        out.println(lobby.toString());
    }

    /**
     * Wait for ready status
     */


    public void waitStart()throws IOException {
        System.out.println("Waiting for game start");

        while (!lobby.hasTimerStarted()){
            out.println("*PING*");
            in.readLine();
        }
        out.println("*timer_started*");
        while (!lobby.hasStarted()){
            out.println("*PING*");
            in.readLine();
        }
        out.println("*started*");
        System.out.println("sending start signal");

    }

    public void game() {
        manager=new GameManagerSocket(this);
    }

    public void run() {
        try {
            System.out.println("Thread started");
            if(!lobby.hasStarted()) {

                    waitLogin();
                    waitPref();
                    waitStart();
                    game();



            }else{
                waitLoginGS();
            }
        } catch (IOException e) {
            System.err.println("Smoething went wrong, removing"+ client.getInetAddress());
            lobby.disconnectPlayer(this);
            this.interrupt();
    }
    }



    public Player getOwner() {
        return owner;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public int getMapPref() {
        return mapPref;
    }

    public void setMapPref(int mapPref) {
        this.mapPref = mapPref;
    }

    public int getKillPref() {
        return killPref;
    }

    public void setKillPref(int killPref) {
        this.killPref = killPref;
    }

    public boolean isTerminatorPref() {
        return terminatorPref;
    }

    public void setTerminatorPref(boolean terminatorPref) {
        this.terminatorPref = terminatorPref;
    }

    public boolean isFinalFrenzyPref() {
        return finalFrenzyPref;
    }

    public void setFinalFrenzyPref(boolean finalFrenzyPref) {
        this.finalFrenzyPref = finalFrenzyPref;
    }

    @Override
    public synchronized String toString() {
        return owner.getUsername() + "," + owner.getCharacter();
    }
}

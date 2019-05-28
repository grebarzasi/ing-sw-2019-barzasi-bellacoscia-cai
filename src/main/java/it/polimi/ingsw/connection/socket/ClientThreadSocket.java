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

    //preferences
    private String mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;
    private boolean waiting=false;

    public ClientThreadSocket(Socket s, Lobby lobby) throws IOException{
        this.lobby=lobby;
        this.client = s;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
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
     * Wait for game settings from client.
     */


    public void waitPref()throws IOException {
        System.out.println("Waiting pref for "+ owner.getUsername());
        mapPref=in.readLine();
        killPref=Integer.parseInt(in.readLine());
        terminatorPref=Boolean.parseBoolean(in.readLine());
        finalFrenzyPref=Boolean.parseBoolean(in.readLine());
        out.println("accepted");
        System.out.println(owner.getUsername() + " pref registered");
        waiting=true;
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


    public void waitReady()throws IOException {
        System.out.println("Waiting ready for " + owner.getUsername());
        while (!lobby.hasStarted()) {
            String readyStatus = in.readLine();
            if(readyStatus.equals("ready")) {
                ready = true;
                System.out.println(owner.getUsername() + "is ready!");
            }else if(readyStatus.equals("not ready")){
                ready = false;
                System.out.println(owner.getUsername() + "is NOT ready!");
            }
            lobby.updateClients();
        }
    }

    public void run() {
        System.out.println("Thread started");
        try {
            waitLogin();
            waitPref();
            while(true)
                lobby.updateClients();

        } catch (IOException e) {
            e.printStackTrace();
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

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    @Override
    public String toString() {
        return owner.getUsername() + "," + owner.getCharacter() + "," + ready;
    }
}

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

    //preferences
    private String mapPref;
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

    public void waitPref()throws IOException {
        System.out.println("Waiting pref for "+ owner.getUsername());
        mapPref=in.readLine();
        killPref=Integer.parseInt(in.readLine());
        terminatorPref=Boolean.parseBoolean(in.readLine());
        finalFrenzyPref=Boolean.parseBoolean(in.readLine());
        out.println("accepted");
        System.out.println(owner.getUsername() + " pref registered");
    }

    public void updateLobby(){
    }

    public void run() {
        System.out.println("Thread started");
        try {
            waitLogin();
            waitPref();

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
}

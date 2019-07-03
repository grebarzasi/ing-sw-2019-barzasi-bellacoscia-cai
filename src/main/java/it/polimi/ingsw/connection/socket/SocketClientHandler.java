package it.polimi.ingsw.connection.socket;

import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.connection.ClientHandler;
import it.polimi.ingsw.connection.ServerCommManager;

import java.io.*;
import java.net.Socket;

import static it.polimi.ingsw.connection.ServerMessage.THREAD_PRIORITY;

/**
 * handle the clients connected using socket technology
 */
public class SocketClientHandler extends ClientHandler {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    public SocketClientHandler(Lobby lobby){
        super(lobby,30);
    }

    public SocketClientHandler(Socket s, Lobby lobby, int countdown) throws IOException{
        super(lobby, countdown);
        this.client = s;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())),true);
    }

    public void game() {
        super.getOwner().setView(new ServerCommManager(this,super.getCountdown()));
        ((ServerCommManager) super.getOwner().getView()).setPriority(THREAD_PRIORITY);
        ((ServerCommManager) super.getOwner().getView()).start();
    }


    /**
     * Login procedure: read user and character, if lobby accepts them reply to client "accepted" .
     * if lobby refuse the login reply "refused" and keep waiting for login until a valid login is achieved.
     */
    public boolean login()throws IOException {
        do {
            System.out.println("Waiting login");
            String username=in.readLine();
            String character=in.readLine();
            super.setOwner(new Player(username,character));
            if(super.getLobby().hasStarted()&&super.getLobby().reconnectPlayer(this)){
                out.println("reconnected");
                System.out.println("Reconnecting " + super.getOwner().getUsername());
                return true;
            }

            if (super.getLobby().addPlayer(this)) {
                out.println("accepted");
                System.out.println("repling to " + super.getOwner().getUsername() + ": accepted!");
                break;
            } else {
                out.println("refused");
                System.out.println("repling to " + username + ": refused!");
            }
        }while(true);
        return true;
    }

    public void waitPref()throws IOException {
        System.out.println("Waiting pref for "+ super.getOwner().getUsername());
        super.setMapPref(Integer.parseInt(in.readLine()));
        super.setKillPref(Integer.parseInt(in.readLine()));
        super.setTerminatorPref(Boolean.parseBoolean(in.readLine()));
        super.setFinalFrenzyPref(Boolean.parseBoolean(in.readLine()));
        out.println("accepted");
        System.out.println(super.getOwner().getUsername() + " pref registered\n");
        super.setReady(true);
        super.getLobby().updateClients();
    }

    public void updateLobby(){
        out.println(super.getLobby().toString());
    }

    /**
     * Wait for ready status
     */


    public void waitStart()throws IOException {
        System.out.println("Waiting for game start");

        while (!super.getLobby().hasTimerStarted()){
            out.println("*PING*");
            in.readLine();
        }
        out.println("*timer_started*");
        while (!super.getLobby().hasStarted()){
            out.println("*PING*");
            in.readLine();
        }
        out.println("*started*");
        System.out.println("sending start signal");


    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
    public BufferedReader getIn() {
        return in;
    }

    public boolean isRmi(){
        return false;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void run() {
        try {
            System.out.println("Thread started");


                login();
                if(super.getLobby().hasStarted()){
                    return;
                }
                waitPref();
                waitStart();
                game();

        } catch (IOException e) {
            System.err.println("Smoething went wrong, removing"+ client.getInetAddress());
            super.getLobby().disconnectPlayer(this);
            this.interrupt();
        }
    }
}

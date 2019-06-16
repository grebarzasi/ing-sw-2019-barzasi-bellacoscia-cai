package it.polimi.ingsw.connection;


import it.polimi.ingsw.Lobby;
import it.polimi.ingsw.Player;
import it.polimi.ingsw.View;

import java.io.*;

import java.util.Timer;

/**
 * A thread for each client that handle the communication using Socket tech
 * @author Gregorio Barzasi
 */
public abstract class ClientHandler extends Thread {

    private static final int DISCONNECT_TIMER = 30;
    private Lobby lobby;
    private Player owner;
    private boolean ready=false;
    private boolean expired=false;
    private boolean disconnected=false;

    private Timer disconnectCountdown;
    private Timer expiredCountdown;


    //preferences
    private int mapPref;
    private int killPref;
    private boolean terminatorPref;
    private boolean finalFrenzyPref;

    public ClientHandler(Lobby lobby){
        this.lobby=lobby;
        this.owner = new Player();
        this.disconnectCountdown=new Timer();
        this.expiredCountdown= new Timer();
    }



    /**
     * sends updates to client
     */
    public abstract void updateLobby();

    /**
     * Wait for ready status
     */


    public abstract void waitStart()throws IOException ;

    public abstract void game();

//    public abstract void disconnectReset();

    public abstract void run();



    public Player getOwner() {
        return owner;
    }


    public void setOwner(Player owner) {
        this.owner=owner;
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



    public int getMapPref() {
        return mapPref;
    }

    public void setMapPref(int mapPref) {
        this.mapPref = mapPref;
    }

    public int getKillPref() {
        return killPref;
    }

    public Lobby getLobby() {
        return lobby;
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

    public boolean isDisconnected() {
        return disconnected;
    }

    public void setDisconnected(boolean disconnected) {
        this.disconnected = disconnected;
    }

    @Override
    public synchronized String toString() {
        return owner.getUsername() + "," + owner.getCharacter();
    }
}


package it.polimi.ingsw.Connection;

import it.polimi.ingsw.Player;

import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Client thread
 *
 * @author Carlo Bellacoscia
 */
public class ClientHandler extends Thread {

    private ArrayList<String> playerConnected = new ArrayList<>();
    private String username;
    private String character;
    ServerSocket serverSocket;

    public ClientHandler(Player p, ServerSocket s) {
        this.username = p.getUsername();
        this.character = p.getCharacter();
        this.playerConnected.add(username);

        this.serverSocket = s;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public ArrayList<String> getPlayerConnected() {
        return playerConnected;
    }

    @Override
    public void run() {
        super.run();

    }
}

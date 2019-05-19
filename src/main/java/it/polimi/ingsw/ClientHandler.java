package it.polimi.ingsw;

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

    public ClientHandler(Player p) {
        this.username = p.getUsername();
        this.character = p.getCharacter();
        this.playerConnected.add(username);

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

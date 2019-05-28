package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.Lobby;

import java.io.IOException;

public class LobbyUpTh extends Thread {
    VirtualLobby v;
    public LobbyUpTh(VirtualLobby v) {
        this.v=v;
    }

    @Override
    public void run() {
        while(!v.isGameStarted()) {
            try {
                v.waitUpdate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package it.polimi.ingsw;

import it.polimi.ingsw.connection.socket.ClientThreadSocket;

public class DisconnectChecker extends Thread {

    private Lobby lobby;
    public DisconnectChecker(Lobby lobby){
        this.lobby=lobby;
    }
    @Override
    public synchronized void run() {
            while (true) {
                for (ClientThreadSocket c : lobby.getJoinedPlayers()) {
                    System.out.println("test");
                    if (!c.getClient().isConnected())
                        System.out.println("disc");

                    try {
                       this.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

}

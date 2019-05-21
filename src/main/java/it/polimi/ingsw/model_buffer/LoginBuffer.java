package it.polimi.ingsw.model_buffer;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.rmi.RmiClient;
import java.rmi.RemoteException;

public class LoginBuffer {
    private ConnectionTech connController;
    private String username;
    private String character;

    public LoginBuffer(String username, String character,ConnectionTech connController) {
        this.username = username;
        this.character = character;
        this.connController=connController;
    }

    ;

    public void send() throws RemoteException {
        if (connController.isRmi()) {
            ((RmiClient) connController).getServerRmi().login(this.username, this.character);
        } else {
            ((SClient) connController).getOutput().println("login");
            ((SClient) connController).getOutput().println(username);
            ((SClient) connController).getOutput().println(character);
            System.out.println(username + "logged as:"+ character);
        }
    }
}

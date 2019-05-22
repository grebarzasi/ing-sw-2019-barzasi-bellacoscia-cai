package it.polimi.ingsw.model_buffer;

import it.polimi.ingsw.connection.ConnectionTech;
import it.polimi.ingsw.connection.socket.SClient;
import it.polimi.ingsw.connection.rmi.RmiClient;

import java.io.IOException;
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


    public boolean send() throws IOException {
        boolean flag=false;

        if (connController.isRmi()) {
            flag = ((RmiClient) connController).getServerRmi().login(this.username, this.character);
        } else {
            ((SClient) connController).getOutput().println("login");
            ((SClient) connController).getOutput().println("login");
            System.out.println("sending");
            ((SClient) connController).getOutput().println(username);
            ((SClient) connController).getOutput().println(character);
            while(((SClient) connController).getInput().readLine()!="reply");
            if(((SClient) connController).getInput().readLine()!="accepted"){
                flag=true;
            }
        }
        return flag;
    }
}

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
            SClient c = ((SClient) connController);
            System.out.println("sending");
            c.getOutput().println(username);
            c.getOutput().println(character);
            String reply = c.getInput().readLine();
            if(reply.equals("accepted")){
                System.out.println(reply);
                flag=true;
            }else if (reply.equals("refused")){
                System.out.println(reply);
            }
        }
        return flag;
    }
}

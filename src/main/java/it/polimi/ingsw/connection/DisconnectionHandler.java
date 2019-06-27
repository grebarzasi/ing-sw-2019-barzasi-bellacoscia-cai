package it.polimi.ingsw.connection;

import static it.polimi.ingsw.CLI.CliMessages.CONNECTION_ERR;
import static it.polimi.ingsw.connection.ServerMessage.RMI_S_ERR;
import static it.polimi.ingsw.connection.ServerMessage.SOCKET_S_ERR;

public class DisconnectionHandler {
    public static void server(boolean rmi){
        if(rmi)
            System.out.println(RMI_S_ERR);
        else
            System.out.println(SOCKET_S_ERR);

    }

    public static void client(boolean rmi){
        System.err.println(CONNECTION_ERR);
    }

    public static void generic(){
        System.err.println(CONNECTION_ERR);
    }
}


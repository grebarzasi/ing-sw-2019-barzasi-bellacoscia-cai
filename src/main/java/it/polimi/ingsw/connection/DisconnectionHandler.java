package it.polimi.ingsw.connection;

import static it.polimi.ingsw.Color.RED;
import static it.polimi.ingsw.Color.RESET;

        /**
        * Called when a disconnection occurs
        * @author Gregorio Barzasi
        */
public class DisconnectionHandler {

    public static final String CONNECTION_ERR = "Qualcosa è andato storto, verifica la connessione con il server!"+RESET;
    public static final String SOCKET_S_ERR=RED+"Socket Server: qualcosa è andato storto!";
    public static final String RMI_S_ERR=RED+"Rmi Server: qualcosa è andato storto!";

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


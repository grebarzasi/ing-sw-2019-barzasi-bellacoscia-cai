package it.polimi.ingsw.connection;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.CLI.CliColor.RED;

public class ServerMessage {
    public static final String RMI_COLOR=PURPLE_BOLD;
    public static final String SOCKET_COLOR=CYAN_BOLD;


    public static final String SERVER_HEAD="--------------------------------\n"+YELLOW_BACKGROUND_BRIGHT+BLUE_BOLD+"*** ADRENALINA SERVER CONFIG ***"+RESET+"\n--------------------------------";

    public static final String RMI_MSG=RMI_COLOR+"[RMI SERVER]";
    public static final String SOCKET_MSG=SOCKET_COLOR+"[SOCKET SERVER]";

    public static final String RMI_S_READY=GREEN+"Rmi Server is READY on port: ";
    public static final String RMI_S_ERR=RED+"Rmi Server: Something went wrong!";
    public static final String SOCKET_S_READY=GREEN+"Socket Server is READY on ";
    public static final String SOCKET_S_CONN=SOCKET_COLOR+"Socket client Connected:";
    public static final String SOCKET_S_ERR=RED+"Socket Server: Something went wrong!";


    public static void clearScreen() {
            final int CLEAR_NUM=20;
            for(int i =0; i<CLEAR_NUM;i++)
                System.out.print("\n");
        }

    }


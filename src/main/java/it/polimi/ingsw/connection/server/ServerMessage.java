package it.polimi.ingsw.connection.server;


import static it.polimi.ingsw.Color.*;

public class ServerMessage {
    public static final int THREAD_PRIORITY=3;

    public static final String PORT_SELECT = PURPLE+"Inserisci la porta: (lascia vuoto per i valori di default)"+RESET;
    public static final String IP_SELECT = PURPLE+"Inserisci l'ip: (lascia vuoto per i valori di default)"+RESET;
    public static final String IP_SELECT_ERR = RED+"Ip non disponibile o non raggiungibile, riprova:"+RESET;
    public static final String PORT_ERR = "Porta non disponibile, cambiala!"+RESET;
    public static final String CONNECTION_ERR = "Qualcosa è andato storto, verifica la connessione con il server!"+RESET;
    public static final String CONNECTION_OK = "Connessione stabilita\n"+RESET;

    public static final String RMI_COLOR=BLUE_BOLD;
    public static final String SOCKET_COLOR=CYAN_BOLD;

    public static final String CLIENT_UNREACHABLE=" disconnesso dal gioco";
    public static final String RETURN_IN_GAME="Sei stato sospeso dal server per inattività, vuoi tornare in gioco?";

    public static final String ADV_MODE = PURPLE_BOLD_BRIGHT+"Modalità avanzata?"+RESET;
    public static final String ASK_TIMER = PURPLE_BOLD_BRIGHT+"Countdown di inattività?(tempo in secondi)"+RESET;

    public static final String TEST_MODE = PURPLE_BOLD_BRIGHT+"Attivare testMode?"+RESET;
    public static final String LINE_SEP ="------------------------------\n"+RESET;
    public static final String GENERIC_N = "\nNon disponibile, ancora:"+RESET;





    public static final String SERVER_HEAD="--------------------------------\n"+YELLOW_BACKGROUND_BRIGHT+BLUE_BOLD+"*** ADRENALINA SERVER CONFIG ***"+RESET+"\n--------------------------------";

    public static final String RMI_MSG=RMI_COLOR+"[RMI SERVER]";
    public static final String SOCKET_MSG=SOCKET_COLOR+"[SOCKET SERVER]";

    public static final String RMI_S_READY=GREEN+"Rmi Server è PRONTO sulla porta: ";
    public static final String RMI_S_ERR=RED+"Rmi Server: qualcosa è andato storto!";
    public static final String SOCKET_S_READY=GREEN+"Socket Server è PRONTO sulla porta: ";
    public static final String SOCKET_S_CONN=SOCKET_COLOR+"Socket client connsesso:";
    public static final String SOCKET_S_ERR=RED+"Socket Server: qualcosa è andato storto!";


    public static void clearScreen() {
            final int CLEAR_NUM=20;
            for(int i =0; i<CLEAR_NUM;i++)
                System.out.print("\n");
        }

    }


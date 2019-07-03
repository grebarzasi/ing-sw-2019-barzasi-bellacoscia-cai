
package it.polimi.ingsw.CLI;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.connection.ConnMessage.INNER_SEP;

/**
 * Collects all messages used in command line interface
 * @author Gregorio Barzasi
 */
public class CliMessages {

    public static final String MSG_COLOR=PURPLE;
    public static final String HEAD_MSG="--------------------------------\n"+YELLOW_BACKGROUND_BRIGHT+BLUE_BOLD+"*** Benvenuti in ADRENALINA! ***"+RESET+"\n--------------------------------";
    public static final String SELECT_CONN = MSG_COLOR+"Seleziona la tipologia di connessione: (1-RMI/2-SOCKET)"+RESET;
    public static final String SELECT_CONN_ERR = RED+"Connessione non disponibile, riprova:"+RESET;


    //LOGIN
    public static final String PRE_LOGIN =MSG_COLOR+ "E' tempo di fare il login!"+RESET;
    public static final String LOGIN_ERR = "Login FALLITO! riprova cambiando username e character!"+RESET;
    public static final String LOGIN_SUCC = "Login ESEGUITO!"+RESET;

    public static final String WAITING = MSG_COLOR+"In attesa..."+RESET;
    public static final String RMI = "RMI Selezionato!"+RESET;
    public static final String SOCKET = "SOCKET Selezionato!"+RESET;
    public static final String DEFAULT = "Valori di default selezionati:"+RESET;

    public static final String PORT_SELECT = MSG_COLOR+"Inserisci la porta: (lascia vuoto per i valori di default)"+RESET;
    public static final String IP_SELECT = MSG_COLOR+"Inserisci l'ip: (lascia vuoto per i valori di default)"+RESET;
    public static final String IP_SELECT_ERR = RED+"Ip non disponibile o non raggiungibile, riprova:"+RESET;

    public static final String PORT_ERR = "Porta non disponibile, cambiala!"+RESET;
    public static final String CONNECTION_ERR = "Qualcosa è andato storto, verifica la connessione con il server!"+RESET;
    public static final String CONNECTION_OK = "Connessione stabilita\n"+RESET;

    public static final String USERNAME =MSG_COLOR+ "Inserisci l'username:"+RESET;
    public static final String CHARACTER = MSG_COLOR+"Inserisci il tuo colore: (red, yellow, blue, grey, green)"+RESET;
    public static final String CHARACTER_ERR = "Colore non disponibile, scegline un altro."+RESET;

    public static final String GENERIC_N = "\nNon disponibile, ancora:"+RESET;
    public static final String LINE_SEP ="------------------------------\n"+RESET;


    /*LOBBY MSG*/
    public static final String LOBBY_HEAD = "\n\n----------------------------------------\n"+YELLOW_BACKGROUND_BRIGHT+BLUE_BOLD+"*** LOBBY! imposta le tue preferenze! ***"+RESET+"\n----------------------------------------";
    public static final String PREFERENCE_Q =MSG_COLOR+ "\nVuoi modificare qualcosa? (S/N)"+RESET;
    public static final String PREFERENCE_Y = "\nOk,scegli nuovamente:"+RESET;
    public static final String PREFERENCE_N ="\nOK! sto inviando le tue preferenze"+RESET;

    public static final String MAP_OPT = "\n1-large\n2-medium1\n3-medium2\n4-small"+RESET;
    public static final String MAP_Q = MSG_COLOR+"Seleziona la mappa:"+RESET;
    public static final String MAP_Y = "\nPerfetto! hai selezionato la mappa numero "+RESET;
    public static final String MAP_N = "\nMappa non disponibile, ancora:"+RESET;



    //PREFERENCES
    public static final String TERMINATOR_Q =MSG_COLOR+ "\nVuoi assoldare un Terminator bot? (S/N)"+RESET;
    public static final String TERMINATOR_Y = "\nTerminator assoldato, sei coraggioso!"+RESET;
    public static final String TERMINATOR_N = "\nTerminator rifiutato, sarà per la prossima partita."+RESET;

    public static final String FRENZY_Q =MSG_COLOR+ "\nVuoi introdurre la Frenesia Finale? (S/N)"+RESET;
    public static final String FRENZY_Y = "\nFrenesia Finale impostata!"+RESET;
    public static final String FRENZY_N = "\nNessuna Frenesia finale, ok!"+RESET;

    public static final String SKULL_Q = MSG_COLOR+"\nQuanti teschi sulla Killshot Track? (5-8)"+RESET;
    public static final String SKULL_Y = "\nPerfetto! Hai selezionato un numero di teschi pari a "+RESET;
    public static final String SKULL_N = "\nValore non selezionabile,  ancora:"+RESET;


    public static final String WAITINGROOM_HEAD ="\n\n-------------------\n"+YELLOW_BACKGROUND_BRIGHT+BLUE_BOLD +"*** Waiting room..."+RESET +"\n-------------------\n";
    public static final String COUNTDOWN_START = "Countdown avviato!"+RESET;
    public static final String GAME_START = "Gioco Iniziato!"+RESET;
    public static final String TERMINATOR_NAME = "THANOS";


    //Game msg
    public static final String CHOOSE_PU_Q = MSG_COLOR+"\nSeleziona un powerup da utilizzare:"+RESET;
    public static final String CHOOSE_PU_ERR = RED+ "\nNon disponibile"+RESET;

    public static final String CHOOSE_WP_Q =MSG_COLOR+ "\nSeleziona un arma:"+RESET;
    public static final String CHOOSE_WP_ERR = RED+"\nNon disponibile"+RESET;

    public static final String CHOOSE_ACTION_Q = MSG_COLOR+"\nSeleziona un azione:"+RESET;
    public static final String CHOOSE_ACTION_ERR = RED+"\nNon disponibile"+RESET;

    public static final String CHOOSE_TARGET_Q = MSG_COLOR+"\nSeleziona un bersaglio:"+RESET;
    public static final String CHOOSE_TARGET_ERR = RED+"\nNon disponibile"+RESET;

    public static final String CHOOSE_SQUARE_Q = MSG_COLOR+"\nSeleziona una destinazione (riga+colonna):"+RESET;
    public static final String CHOOSE_SQUARE_ERR = RED+"\nNon disponibile"+RESET;


    public static final String SHOW_TARGET_ADV_Q = MSG_COLOR+"\nSeleziona i target"+RESET;
    public static final String SHOW_TARGET_ADV_ERR = RED+"\nNon disponibile"+RESET;
    public static final String SHOW_TARGET_SELECTED = " bersagli selezionati:"+RESET;


    public static final String SHOW_DIRECTION_Q = MSG_COLOR+"Scegli una direzione (N/S/E/O)"+RESET+"\n(Bersagli attualmente colpibili:";
    public static final String SHOW_DIRECTION_ERR = RED+"Direzione non disponibile"+RESET;


    public static final String SHOW_EFFECTS_Q = MSG_COLOR+"\nScegli un effetto da utilizzare:"+RESET;
    public static final String SHOW_EFFECTS_ERR = RED+"Direzione non disponibile"+RESET;


    public static final String BACK = "<";
    public static final String NEXT = ">";


    static void clearScreen() {
        final int CLEAR_NUM=20;
        for(int i =0; i<CLEAR_NUM;i++)
           System.out.print("\n");
    }

}

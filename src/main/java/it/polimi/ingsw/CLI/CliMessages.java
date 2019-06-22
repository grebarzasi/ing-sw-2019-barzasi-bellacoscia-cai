
package it.polimi.ingsw.CLI;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.connection.ConnMessage.INNER_SEP;

/**
 * @author Gregorio Barzasi
 */
public class CliMessages {

    public static final String HEAD_MSG="------------------------------\n"+"*** Benvenuti in ADRENALINA! ***"+"\n------------------------------";
    public static final String SELECT_CONN = "Seleziona la tipologia di connessione: (RMI/SOCKET)";

    //LOGIN
    public static final String PRE_LOGIN = "E' tempo di fare il login!";
    public static final String LOGIN_ERR = "Login FALLITO! riprova cambiando username e character!";
    public static final String LOGIN_SUCC = "Login ESEGUITO!";

    public static final String WAITING = "In attesa...";
    public static final String RMI = "RMI Selezionato!";
    public static final String SOCKET = "SOCKET Selezionato!";
    public static final String DEFAULT = "Valori di default selezionati:";

    public static final String PORT_SELECT = "Inserisci la porta: (lascia vuoto per i valori di default)";
    public static final String IP_SELECT = "Inserisci l'ip: (lascia vuoto per i valori di default)";
    public static final String PORT_ERR = "Porta non disponibile, cambiala!";
    public static final String CONNECTION_ERR = "Qualcosa è andato storto, verifica la connessione con il server!";

    public static final String USERNAME = "Inserisci l'username:";
    public static final String CHARACTER = "Inserisci il tuo colore: (red, yellow, blue, grey, green)";
    public static final String CHARACTER_ERR = "Colore non disponibile, scegline un altro.";

    public static final String GENERIC_N = "\nNon disponibile, ancora:";
    public static final String LINE_SEP ="------------------------------\n";


    /*LOBBY MSG*/
    public static final String LOBBY_HEAD = "\n\n----------------------------------------\n"+"*** LOBBY! imposta le tue preferenze! ***"+"\n----------------------------------------";
    public static final String PREFERENCE_Q = "\nVuoi modificare qualcosa? (Y/N)";
    public static final String PREFERENCE_Y = "\nOk,scegli nuovamente:";
    public static final String PREFERENCE_N = "\nOK! sto inviando le tue preferenze";

    public static final String MAP_OPT = "\n1-large\n2-medium1\n3-medium2\n4-small";
    public static final String MAP_Q = "Seleziona la mappa:";
    public static final String MAP_Y = "\nPerfetto! hai selezionato la mappa numero ";
    public static final String MAP_N = "\nMappa non disponibile, ancora:";

    //PREFERENCES
    public static final String TERMINATOR_Q = "\nVuoi assoldare un Terminator bot? (Y/N)";
    public static final String TERMINATOR_Y = "\nTerminator assoldato, sei coraggioso!";
    public static final String TERMINATOR_N = "\nTerminator rifiutato, sarà per la prossima partita.";

    public static final String FRENZY_Q = "\nVuoi introdurre la Frenesia Finale? (Y/N)";
    public static final String FRENZY_Y = "\nFrenesia Finale impostata!";
    public static final String FRENZY_N = "\nNessuna Frenesia finale, ok!";

    public static final String SKULL_Q = "\nQuanti teschi sulla Killshot Track? (5-8)";
    public static final String SKULL_Y = "\nPerfetto! Hai selezionato un numero di teschi pari a ";
    public static final String SKULL_N = "\nValore non selezionabile,  ancora:";


    public static final String WAITINGROOM_HEAD ="\n\n----------------------------------------\n" +"*** Waiting room..." +"\n----------------------------------------";
    public static final String COUNTDOWN_START = "Countdown avviato!";
    public static final String GAME_START = "Gioco Iniziato!";
    public static final String TERMINATOR_NAME = "THANOS";


    //Game msg
    public static final String CHOOSE_PU_Q = "\nSeleziona un powerup da utilizzare:";
    public static final String CHOOSE_PU_ERR = RED+ "\nNon disponibile";

    public static final String CHOOSE_WP_Q = "\nSeleziona un arma:";
    public static final String CHOOSE_WP_ERR = RED+"\nNon disponibile";

    public static final String CHOOSE_ACTION_Q = "\nSeleziona un azione:";
    public static final String CHOOSE_ACTION_ERR = RED+"\nNon disponibile";

    public static final String CHOOSE_TARGET_Q = "\nSeleziona un bersaglio:";
    public static final String CHOOSE_TARGET_ERR = RED+"\nNon disponibile";

    public static final String CHOOSE_SQUARE_Q = "\nSeleziona una destinazione (riga+colonna):";
    public static final String CHOOSE_SQUARE_ERR = RED+"\nNon disponibile";

    public static final String SHOW_TARGET_ADV_Q = "\nSeleziona i target";
    public static final String SHOW_TARGET_ADV_ERR = RED+"\nNon disponibile";
    public static final String SHOW_TARGET_SELECTED = " bersagli selezionati:";

    public static final String SHOW_DIRECTION_Q = "Scegli una direzione (N/S/E/O)\n(Bersagli attualmente colpibili:";
    public static final String SHOW_DIRECTION_ERR = RED+"Direzione non disponibile";

    public static final String SHOW_EFFECTS_Q = "\nScegli un effetto da utilizzare:";
    public static final String SHOW_EFFECTS_ERR = RED+"Direzione non disponibile";

    public static final String BACK = "<";
    public static final String NEXT = ">";


    public static void clearScreen() {
        final int CLEAR_NUM=20;
        for(int i =0; i<CLEAR_NUM;i++)
           System.out.print("\n");
    }

}

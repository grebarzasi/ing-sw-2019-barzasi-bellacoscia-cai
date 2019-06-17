package it.polimi.ingsw.CLI;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.connection.ConnMessage.INNER_SEP;

/**
 * @author Gregorio Barzasi
 */
public class CliMessages {

    public static final String HEAD_MSG="------------------------------\n"+"*** Welcome to ADRENALINA! ***"+"\n------------------------------";
    public static final String SELECT_CONN = "Select connection type: (RMI/SOCKET)";

    //LOGIN
    public static final String PRE_LOGIN = "It's time to login!";
    public static final String LOGIN_ERR = "Login failed! name or character already in use, try again!";
    public static final String LOGIN_SUCC = "Login success!";

    public static final String WAITING = "Waiting...";
    public static final String RMI = "RMI Selected!";
    public static final String SOCKET = "SOCKET Selected!";
    public static final String DEFAULT = "Default value selected:";

    public static final String PORT_SELECT = "Insert port: (leave empty for default value)";
    public static final String IP_SELECT = "Insert ip: (leave empty for default value)";
    public static final String PORT_ERR = "Not available port, insert another port:";
    public static final String CONNECTION_ERR = "Something went wrong, check server connection";

    public static final String USERNAME = "Insert username:";
    public static final String CHARACTER = "Insert your color";
    public static final String CHARACTER_ERR = "Not available color, insert another color:";

    public static final String GENERIC_N = "\nNot available,  again:";
    public static final String LINE_SEP ="------------------------------\n";


    /*LOBBY MSG*/
    public static final String LOBBY_HEAD = "\n\n----------------------------------------\n"+"*** LOBBY! Setup your game preferences! ***"+"\n----------------------------------------";
    public static final String PREFERENCE_Q = "\nYou want to edit something? (Y/N)";
    public static final String PREFERENCE_Y = "\nOk,check again:";
    public static final String PREFERENCE_N = "\nSending preferences";

    public static final String MAP_OPT = "\n1-large\n2-medium1\n3-medium2\n4-small";
    public static final String MAP_Q = "Select your map:";
    public static final String MAP_Y = "\nPerfect! you selected map number: ";
    public static final String MAP_N = "\nNot available,  again:";

    //PREFERENCES
    public static final String TERMINATOR_Q = "\nTerminator bot? (Y/N)";
    public static final String TERMINATOR_Y = "\nTerminator hired, you're brave!";
    public static final String TERMINATOR_N = "\nTerminator rejected";

    public static final String FRENZY_Q = "\nFinal Frenzy? (Y/N)";
    public static final String FRENZY_Y = "\nFinal frenzy set!";
    public static final String FRENZY_N = "\nNo final frenzy, ok!";

    public static final String SKULL_Q = "\nSkull on Kill Track? (5-8)";
    public static final String SKULL_Y = "\nPerfect! you selected the skulls amount on kill track: ";
    public static final String SKULL_N = "\nOut of range,  again:";


    public static final String WAITINGROOM_HEAD ="\n\n----------------------------------------\n" +"*** Waiting room..." +"\n----------------------------------------";
    public static final String COUNTDOWN_START = "Countdown started!";
    public static final String GAME_START = "Game started";
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

    public static final String CHOOSE_SQUARE_Q = "\nSeleziona una destinazione (riga"+INNER_SEP+"colonna):";
    public static final String CHOOSE_SQUARE_ERR = RED+"\nNon disponibile";

    public static final String SHOW_TARGET_ADV_Q = "\nSeleziona i target";
    public static final String SHOW_TARGET_ADV_ERR = RED+"\nNon disponibile";
    public static final String SHOW_TARGET_SELECTED = " bersagli selezionati:";

    public static final String SHOW_DIRECTION_Q = "Scegli una direzione (N/S/E/O).\n Bersagli attualmente colpibili:";
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

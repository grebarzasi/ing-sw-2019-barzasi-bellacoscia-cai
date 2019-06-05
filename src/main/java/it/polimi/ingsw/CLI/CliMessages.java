package it.polimi.ingsw.CLI;

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

    public static final String PORT_SELECT = "Insert port:";
    public static final String IP_SELECT = "Insert port:";
    public static final String PORT_ERR = "Not available port, insert another port:";
    public static final String CONNECTION_ERR = "Something went wrong, connection error";

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

    public static final String MAP_OPT = "\n1-...\n2-...\n3-...\n4-...";
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

    public static final String SKULL_Q = "\nSkull on Kill Track? (3-8)";
    public static final String SKULL_Y = "\nPerfect! you selected the skulls amount on kill track: ";
    public static final String SKULL_N = "\nOut of range,  again:";


    public static final String WAITINGROOM_HEAD ="\n\n----------------------------------------\n" +"*** Waiting room..." +"\n----------------------------------------";
    public static final String COUNTDOWN_START = "Countdown started!";
    public static final String GAME_START = "Game started";


    public static void clearScreen() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}

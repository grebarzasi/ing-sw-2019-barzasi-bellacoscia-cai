package it.polimi.ingsw.CLI;

/**
 * classes that collect all colors available in terminal
 */
public class CliColor {
    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_B = "\u001B[40m";
    public static final String RED_B = "\u001B[41m";
    public static final String GREEN_B = "\u001B[42m";
    public static final String YELLOW_B = "\u001B[43m";
    public static final String BLUE_B = "\u001B[44m";
    public static final String PURPLE_B = "\u001B[45m";
    public static final String CYAN_B = "\u001B[46m";
    public static final String WHITE_B = "\u001B[47m";


    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public static final String F =YELLOW_BRIGHT+"(_)" +CYAN_BOLD_BRIGHT;

    public static final String ADRENALINA_HEAD = "\n" + YELLOW_BRIGHT+
            "        (     (          )        (    (       )         \n" +
            "   (    )\\ )  )\\ )    ( /(  (     )\\ ) )\\ ) ( /(  (      \n" +
            "   )\\  (()/( (()/(( "+"  )\\()) )\\   (()/((()/( )\\()) )\\     \n" +
            "((((_)( /(_)) "+"/(_))\\ ((_)((((_)(  /(_))/(_)|(_)((((_)(   \n" +
            " )\\ _ )(_))_ (_))((_) _((_)\\ _ )\\(_)) (_))  _((_)\\ _ )\\  \n" +
            " "+F+"_\\"+F+"   \\| _ \\ __| \\| "+F+"_\\"+F+" |  |_ _|| \\| "+F+"_\\"+F+" \n" +
            "  / _ \\ | |) |   / _|| .` |/ _ \\ | |__ | | | .` |/ _ \\   \n" +
            " /_/ \\_\\|___/|_|_\\___|_|\\_/_/ \\_\\|____|___||_|\\_/_/ \\_\\  \n" +
            "                                                         \n"; // BLACK

}

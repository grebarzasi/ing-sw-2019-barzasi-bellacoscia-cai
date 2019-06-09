package it.polimi.ingsw.CLI;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.CLI.CliColor.RESET;

public class CLiBoardStuff {


    public static final int[] KILLSHOT_TRACK = {8,6,4,2};
    public static final int[] DEATH_TRACK = {8,6,4,2,1,1};
    public static final int[] DEATH_TRACK_FRENZY = {2,1,1,1};

    public static final String RED_P = PURPLE_BACKGROUND_BRIGHT+" "+RESET;
    public static final String GREEN_P = GREEN_BACKGROUND_BRIGHT+" "+RESET;
    public static final String BLUE_P =CYAN_BACKGROUND_BRIGHT+" "+RESET;
    public static final String GRAY_P = WHITE_BACKGROUND_BRIGHT+" "+RESET;
    public static final String YELLOW_P = YELLOW_BACKGROUND_BRIGHT+" "+RESET;

    public static final String ARMORY = " ۩ ";
    public static final String AMMO = "ѻ";

    public static final String YELLOW_AMMO = YELLOW+AMMO+RESET;
    public static final String BLUE_AMMO = BLUE+AMMO+RESET;
    public static final String RED_AMMO = RED+AMMO+RESET;
    public static final String PU_AMMO = BLACK+"Ѻ"+RESET;

    public static final String RED_T = PURPLE_BOLD_BRIGHT+"o"+RESET;
    public static final String GREEN_T = GREEN_BOLD_BRIGHT+"o"+RESET;
    public static final String BLUE_T =CYAN_BOLD_BRIGHT+"o"+RESET;
    public static final String GRAY_T = WHITE_BOLD_BRIGHT+"o"+RESET;
    public static final String YELLOW_T = YELLOW_BOLD_BRIGHT+"o"+RESET;

    public static final String SKULL_T = RED+"*"+RESET;

    public static final String WEAPON_CENSORED = "=";

    public static final String BOARD_LINE =WHITE+ "───────────────┬─────────────╫──────────────────────────┤";
    public static final String BOARD_SEP =WHITE+"├"+BOARD_LINE;
    public static final String BOARD_LEFT = WHITE+"│";
    public static final String BOARD_BOTTOM =WHITE+ "└─────────────────────────────╨──────────────────────────┘";


    public static final int CELLS_NUM =12;
    public static final int ROW_NUM =3;
    public static final int COL_NUM =4;
    public static final int PLAYERS_NUM =5;

    public static final int USERNAME_SPACE =10;
    public static final int MARKS_SPACE =10;
    public static final int DAMAGE_SPACE =12;
    public static final int WEAPON_SPACE = 25;
}

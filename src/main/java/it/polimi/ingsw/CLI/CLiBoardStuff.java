package it.polimi.ingsw.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static it.polimi.ingsw.CLI.CliColor.*;
import static it.polimi.ingsw.CLI.CliColor.RESET;

public class CLiBoardStuff {


    public static final int[] KILLSHOT_TRACK = {8,6,4,2};
    public static final int[] DEATH_TRACK = {8,6,4,2,1,1};
    public static final int[] DEATH_TRACK_FRENZY = {2,1,1,1,0,0};

    public static final String RED_P = PURPLE_BACKGROUND_BRIGHT+" "+RESET;
    public static final String GREEN_P = GREEN_BACKGROUND_BRIGHT+" "+RESET;
    public static final String BLUE_P =CYAN_BACKGROUND_BRIGHT+" "+RESET;
    public static final String GREY_P = WHITE_B+" "+RESET;
    public static final String YELLOW_P = YELLOW_BACKGROUND_BRIGHT+" "+RESET;



    public static final String ARMORY = " W";
    //public static final String ARMORY = " ۩ ";
    public static final String AMMO = "+";
    //public static final String AMMO = "ѻ";


    public static final String YELLOW_AMMO = YELLOW+AMMO+RESET;
    public static final String BLUE_AMMO = BLUE+AMMO+RESET;
    public static final String RED_AMMO = RED+AMMO+RESET;
    public static final String PU_AMMO = BLACK+"§"+RESET;
    //public static final String PU_AMMO = BLACK+"Ѻ"+RESET;

    //public static final String TOKEN = BLACK+"Ѻ"+RESET;
    public static final String RED_T = PURPLE_BOLD_BRIGHT+"o"+RESET;
    public static final String GREEN_T = GREEN_BOLD_BRIGHT+"o"+RESET;
    public static final String BLUE_T =CYAN_BOLD_BRIGHT+"o"+RESET;
    public static final String GREY_T = WHITE_BOLD_BRIGHT+"o"+RESET;
    public static final String YELLOW_T = YELLOW_BOLD_BRIGHT+"o"+RESET;

    public static final String SKULL_T = RED+"*"+RESET;

    public static final String WEAPON_CENSORED = "=";

    public static final String BOARD_LINE =WHITE+    "───────────────┬─────────────╫──────────────────────────┤";
    public static final String BOARD_SEP =WHITE+     "├"+BOARD_LINE;
    public static final String BOARD_LEFT = WHITE+   "│";
    public static final String BOARD_BOTTOM =WHITE+  "└─────────────────────────────╨──────────────────────────┘";


    public static final int CELLS_NUM =12;
    public static final int ROW_NUM =3;
    public static final int COL_NUM =4;
    public static final int PLAYERS_NUM =5;

    public static final int USERNAME_SPACE =10;
    public static final int MARKS_SPACE =10;
    public static final int DAMAGE_SPACE =12;
    public static final int WEAPON_SPACE = 25;
    public static final int KILLSHOT_SPACE = 49;
    public static final int ARMORY_SPACE = 30;




    public static final ArrayList<String> ALL_CHARACTERS=new ArrayList<>(Arrays.asList("blue", "red", "yellow", "grey", "green"));
    public static final ArrayList<String> ALL_AMMO=new ArrayList<>(Arrays.asList("B", "R", "Y"));
    public static final HashMap<String,String> ACTION_TRANSLATION= new HashMap<>();
    static {
        ACTION_TRANSLATION.put("move", "Muovi");
        ACTION_TRANSLATION.put("pick", "Raccogli");
        ACTION_TRANSLATION.put("discard_powerup", "Converti PowerUp");
        ACTION_TRANSLATION.put("powerup", "Usa PowerUp");
        ACTION_TRANSLATION.put("end_turn", "Termina turno");
        ACTION_TRANSLATION.put("shoot", "Spara");
        ACTION_TRANSLATION.put("use_bot", "Usa il Terminator");
        ACTION_TRANSLATION.put("frenzy_shoot", "Colpo frenetico");
        ACTION_TRANSLATION.put("reload", "Ricarica");

    }

    public static final String NORTH = "n";
    public static final String SOUTH = "s";
    public static final String EAST ="e";
    public static final String WEST = "o";
    public static final ArrayList<String> ALL_DIRECTIONS = new ArrayList<>(Arrays.asList(NORTH,SOUTH,EAST,WEST));
}

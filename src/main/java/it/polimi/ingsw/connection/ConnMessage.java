package it.polimi.ingsw.connection;

/**
        * Communication messages
        * @author Gregorio Barzasi
        */
public class ConnMessage {

    public static final String PING = "*PING*";
    public static final String PONG = "*PONG*";
    public static final String AKN = "*OK*";
    public static final String SHOW_PU = "*SHOW_PowerUP*";

    public static final String SHOW_TARGET_ADV = "*SHOW_Target_adv*";
    public static final String SHOW_WEAPONS = "*SHOW_Weapons*";
    public static final String SHOW_ACTIONS = "*SHOW_Action*";
    public static final String SHOW_MOVES = "*SHOW_Moves*";
    public static final String SHOW_TARGETS = "*SHOW_MultipleTarget*";
    public static final String SHOW_SINGLE_TARGET = "*SHOW_SingleTarget*";
    public static final String SHOW_BOOLEAN = "*SHOW_Boolean*";
    public static final String SHOW_MESSAGE = "*SHOW_Message*";
    public static final String DISPLAY_LEADERBOARD = "*DISPLAY_Leaderboard*";
    public static final String SHOW_EFFECTS = "*SHOW_Effects*";

    public static final String CHOOSE_DIRECTION = "*CHOOSE_Direction";
    public static final String UPDATE = "*UPDATE*";
    public static final String NOTHING = "*NULL*";
    public static final String INFO_SEP = ";";
    public static final String INNER_SEP = ":";
    public static final int COUNTDOWN = 5;

}

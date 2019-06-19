package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.connection.ClientHandler;

import java.util.ArrayList;
import java.util.Set;

public interface View {

    /**
     * Shows the PowerUps and makes the user choose one, then returns it
     *
     * @param args the Powerups to show
     * @return the chosen one
     */

    PowerUp showPowerUp(ArrayList<PowerUp> args);

    /**
     * Shows the Weapons and makes the user choose one, the returns is
     * @param args the Weapons to show
     * @return the chosen one
     */

    Weapon showWeapon(ArrayList<Weapon> args);

    /**
     * Show the possible moves that a player can perform then makes the user choose one,
     * then returns it
     * @param args the moves to show
     * @return the chosen one
     */

    Action showActions(ArrayList<Action> args);

    /**
     * Shows the possible destinations a figure can reach and then returns the chosen value
     * @param args the possible destinations
     * @param show FOR CLI ONLY: if true show options first, if false only ask for target
     * @return the chosen one
     */

    Square showPossibleMoves(ArrayList<Square> args, Boolean show);

    /**
     * Shows the effects of the weapon a player can choose from
     * @param args the list of effects
     * @return the one chosen by the player
     */

    Effect showEffects(Set<Effect> args);

    /**
     * Makes the player choose the targets from a single direction
     * @param args the potential figure a player can hit
     * @return the direction on which the targets will be hit
     */

    String chooseDirection(ArrayList<Figure> args);

    /**
     * Displays a message and makes the user make a boolean choice
     *
     * @param message the question's message
     * @return the user's choice
     */

    Boolean showBoolean(String message);

    /**
     * Displays a message to the user
     *
     * @param message the message
     */

    void displayMessage(String message);

    /**
     * Displays the leaderboard of the game to the user
     */

    void displayLeaderboard();

    /**
     * Updates the update to the players' clients
     * @param s the string message containing the update
     * @return outcome of the update
     */

    boolean sendsUpdate(String s);

    /**
     * Displays the list of figures a player can target with an effect of a weapon
     * or a powerup
     * @param args the list of targetable figures
     * @param maxNum maximum of targets that can be selected
     * @param fromDiffSquare true if the player cannot select targets from his own square, false otherwise
     * @param msg description of the action
     * @return the selected targets
     */

    ArrayList<Figure> showTargetAdvanced(Set<Figure> args,int maxNum,boolean fromDiffSquare, String msg);

    boolean isDisconnected();
    void setDisconnected(boolean b);
    boolean isInactive();
    void setInactive(boolean b);

    void reconnectPlayer(ClientHandler b);




}

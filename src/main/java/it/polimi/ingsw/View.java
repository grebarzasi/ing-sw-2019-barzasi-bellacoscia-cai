package it.polimi.ingsw;

import it.polimi.ingsw.controller.actions.Action;
import it.polimi.ingsw.model.board.map.Square;
import it.polimi.ingsw.model.cards.power_up.PowerUp;
import it.polimi.ingsw.model.cards.weapon.Effect;
import it.polimi.ingsw.model.cards.weapon.Weapon;
import it.polimi.ingsw.controller.client_handler.ClientHandler;
import it.polimi.ingsw.model.Figure;

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
     * @param show FOR command_line_view ONLY: if true show options first, if false only ask for target
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
     * Displays the leaderboard of the game given players' ranking
     * @param args the Players ranked by score and priority on the killshot track if even
     */

    void displayLeaderboard(ArrayList<Figure> args);

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

    /**
     * Verifies if the player is disconnected form the game
     * @return true if the player is disconnected, false otherwise
     */
    boolean isDisconnected();

    void setDisconnected(boolean b);

    /**
     * Checks the inactivity status of the player
     * @return true if the player is inactive, false otherwise
     */
    boolean isInactive();

    void setInactive(boolean b);

    /**
     * Reconnects the player from a cient handling thread
     * @param b the thread to reconnect the player to
     */

    void reconnectPlayer(ClientHandler b);


}

package it.polimi.ingsw.virtual_model;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.util.ArrayList;
import java.util.Set;

public interface ViewClient {

    /**
     * Shows the PowerUps and makes the user choose one, then returns it
     *
     * @param args the Powerups to show
     * @return the chosen one
     */

    public String showPowerUp(ArrayList<String> args);

    /**
     * Shows the Weapons and makes the user choose one, the returns is
     * @param args the Weapons to show
     * @return the chosen one
     */

    public String showWeapon(ArrayList<String> args);

    /**
     * Show the possible moves that a player can perform then makes the user choose one,
     * then returns it
     * @param args the moves to show
     * @return the chosen one
     */

    public String showAction(ArrayList<String> args);

    /**
     * Shows the possible destinations a figure can reach and then returns the chosen value
     * @param args the possible destinations
     * @return the chosen one
     */

    public String showPossibleMoves(ArrayList<String> args);

    /**
     * Shows the valid targets a Figure can target, then returns the targets the user decides
     * to hit
     * @param args the valid targets
     * @return the chosen targets
     */

    public String showTargets(ArrayList<String> args);

    /**
     * returns the chosen one direction
     * @return the chosen one
     */

    public String chooseDirection();

    /**
     * Displays a message and makes the user make a boolean choice
     *
     * @param message the question's message
     * @return the user's choice
     */

    public boolean showBoolean(String message);

    /**
     * Displays a message to the user
     *
     * @param message the message
     */

    public void displayMessage(String message);

    /**
     * Displays the leaderboard of the game to the user
     */

    public void displayLeaderboard();

    public String showEffects(Set<String> args);

    public void updateModel(String message);

    /**
     * Displays a message to the user
     *
     * @param message the message
     */

}

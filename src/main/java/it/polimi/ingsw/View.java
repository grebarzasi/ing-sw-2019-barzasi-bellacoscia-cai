package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.controller.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface View {

    /**
     * Shows the PowerUps and makes the user command one, then returns it
     *
     * @param args the Powerups to show
     * @return the chosen one
     */

    public PowerUp showPowerUp(ArrayList<PowerUp> args);

    /**
     * Shows the Weapons and makes the user command one, the returns is
     * @param args the Weapons to show
     * @return the chosen one
     */

    public Weapon showWeapon(ArrayList<Weapon> args);

    /**
     * Show the possible moves that a player can perform then makes the user command one,
     * then returns it
     * @param args the moves to show
     * @return the chosen one
     */

    public String showMoves(Set<String> args);

    /**
     * Shows the possible destinations a figure can reach and then returns the chosen value
     * @param args the possible destinations
     * @return the chosen one
     */

    public Square showPossibleMoves(ArrayList<Square> args);

    /**
     * Shows the valid targets a Figure can target, then returns the targets the user decides
     * to hit
     * @param args the valid targets
     * @return the chosen targets
     */

    public ArrayList<Figure> showMultipleTargets(ArrayList<Figure> args);

    /**
     * Shows the valid targets a Figure can target, then returns the chosen one
     * @param args the valid targets
     * @return the chosen one
     */

    public Figure singleTargetingShowTarget(ArrayList<Figure> args);

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

    public Effect showEffects(Set<Effect> args);

}

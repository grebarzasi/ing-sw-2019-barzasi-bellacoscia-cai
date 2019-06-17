package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Effect;
import it.polimi.ingsw.cards.weapon.Weapon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public interface View {

    /**
     * Shows the PowerUps and makes the user choose one, then returns it
     *
     * @param args the Powerups to show
     * @return the chosen one
     */

    public PowerUp showPowerUp(ArrayList<PowerUp> args)throws IOException;

    /**
     * Shows the Weapons and makes the user choose one, the returns is
     * @param args the Weapons to show
     * @return the chosen one
     */

    public Weapon showWeapon(ArrayList<Weapon> args)throws IOException;

    /**
     * Show the possible moves that a player can perform then makes the user choose one,
     * then returns it
     * @param args the moves to show
     * @return the chosen one
     */

    public Action showActions(ArrayList<Action> args)throws IOException;

    /**
     * Shows the possible destinations a figure can reach and then returns the chosen value
     * @param args the possible destinations
     * @param show FOR CLI ONLY: if true show options first, if false only ask for target
     * @return the chosen one
     */

    public Square showPossibleMoves(ArrayList<Square> args, Boolean show)throws IOException;

    /**
     * Shows the valid targets a Figure can target, then returns the targets the user decides
     * to hit
     * @param args the valid targets
     * @return the chosen targets
     */

//    public ArrayList<Figure> showTargets(ArrayList<Figure> args);

    /**
     * Shows the valid targets a Figure can target, then returns the chosen one
     * @param args the valid targets
     * @return the chosen one
     */

    public String chooseDirection(ArrayList<Figure> args)throws IOException;

    /**
     * Displays a message and makes the user make a boolean choice
     *
     * @param message the question's message
     * @return the user's choice
     */
//    public Figure singleTargetingShowTarget(ArrayList<Figure> args);

    /**
     * Displays a message and makes the user make a boolean choice
     *
     * @param message the question's message
     * @return the user's choice
     */

    public Boolean showBoolean(String message)throws IOException;

    /**
     * Displays a message to the user
     *
     * @param message the message
     */

    public void displayMessage(String message)throws IOException;

    /**
     * Displays the leaderboard of the game to the user
     */

    public void displayLeaderboard()throws IOException;

    public boolean sendsUpdate(String s)throws IOException;

    public Effect showEffects(Set<Effect> args)throws IOException;

    public ArrayList<Figure> showTargetAdvanced(Set<Figure> args,int maxNum,boolean fromDiffSquare, String msg)throws IOException;


    }

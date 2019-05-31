package it.polimi.ingsw;

import it.polimi.ingsw.actions.Action;
import it.polimi.ingsw.board.map.Square;
import it.polimi.ingsw.cards.power_up.PowerUp;
import it.polimi.ingsw.cards.weapon.Weapon;
import it.polimi.ingsw.controller.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public interface View {

    public PowerUp showPowerUp(ArrayList<PowerUp> args);

    public Weapon showWeapon(ArrayList<Weapon> args);

    public String showMoves(Set<String> args);

    public Square showPossibleMoves(ArrayList<Square> args);

    public Player showTarget(Figure arg);

    public ArrayList<Figure> showMultipleTargets(ArrayList<Figure> args);

    public Figure singleTargetingShowTarget(ArrayList<Figure> args);

    public boolean showBoolean();

    public void displayMessage(String string);

}
